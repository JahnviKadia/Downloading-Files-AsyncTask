package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MyService extends Service {

    private static JsonParsing obj;
    private static String result = " ";
    private Thread thread;
    private static final String Tag="FirstService";
    private boolean isRunning  = false;
    private  int file_length = 0;
    private int total = 0;
    private static int counter = 0;
    private static int num;


    public MyService() {
    }


    final class TheThread implements Runnable{

            int serviceId;

            TheThread(int serviceId){
                this.serviceId = serviceId;
            }


            @Override
            public void run() {
                synchronized (this){

                            obj = new JsonParsing();
                            obj.execute();

                }
            }
    }

    @Override
    public void onCreate(){
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        isRunning = true;

    }

    @Override
    public int onStartCommand(Intent intent, int flagId, int startId){
        super.onStartCommand(intent, flagId, startId);
        Toast.makeText(this, "onStartCommand()", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();
        Frag1.txt.setText("");
        thread = new Thread(new TheThread(startId));
        thread.start();


        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        isRunning = false;
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    private class JsonParsing extends AsyncTask<String, Integer, String> {


        private static final String TAG = "Valid URL" ;
        final String[] url_1 = new String[1];
        StringBuilder sb;
        OutputStream output;
        ProgressDialog progressDialog;
        int count;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... f_url) {

            URL url = null;
            try {
                for (num = counter; num <= 10; num++) {
                        url_1[0] = "https://petwear.in/mc2022/news/news_" + num + ".json";
                        System.out.println(url_1[0]);
                        url = new URL(url_1[0]);
                        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
                        conection.setRequestMethod("GET");
                        conection.connect();


                    int file_length = conection.getContentLength();
                        if(file_length == -1){
                            Log.i(TAG, "Not Sucess");
                            Frag1.txt.setText("Downloading Stopped");
                            break;
                        }
                        File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Documents/FIRST1");
                        if (!new_folder.exists()) {
                            if (new_folder.mkdirs()) {
                                Log.i("Info", "Folder succesfully created"); }
                            else {
                                Log.i("Info", "Failed to create folder"); }
                        } else {
                            Log.i("Info", "Folder already exists");
                        }
                        File output_file = new File(new_folder, "download_first.json");
                        Thread.sleep(10000);
                        output = new FileOutputStream(output_file + " " + num);
                        InputStream input = new BufferedInputStream(url.openStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        sb = new StringBuilder();
                        String line = " ";

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + '\n');
                        }
                        result = sb.toString();
                    try {
                        JSONObject root = new JSONObject(result);
                        String activity = root.getString("title");
                        System.out.println(activity);
                        Frag1.txt.setText(activity);
                        //MainActivity.textView4.setText(activity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                            counter = (num+1);


                        output.flush();
                        output.close();
                        input.close();

                        if(!isRunning){
                            return result;
                        }


                }
            }
            catch (IOException e) {
                e.printStackTrace(); }
            catch (InterruptedException e) {
                e.printStackTrace(); }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject root = new JSONObject(result);
                String activity = root.getString("title");
                System.out.println(activity);
                Frag1.txt.setText(activity);
                //MainActivity.textView4.setText(activity);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
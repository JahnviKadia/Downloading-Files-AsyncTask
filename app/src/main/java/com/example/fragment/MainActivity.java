package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "NetworkStatusExample";
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }


            getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, new BlankFragment()).commit();

           // FragmentManager fragmentTransaction = getSupportFragmentManager();
           // FragmentTransaction ft = fragmentTransaction.beginTransaction();
           // ft.add(R.id.fragment_container, new Frag1());
           // ft.add(R.id.fragment_container, new frag2());
           // ft.commit();

        }
    }
}
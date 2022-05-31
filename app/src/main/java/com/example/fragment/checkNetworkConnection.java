package com.example.fragment;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class checkNetworkConnection {

    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;

    public static int isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        if (null != networkInfo) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            }

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }

        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = checkNetworkConnection.isConnected(context);
        String status = null;
        if (conn == checkNetworkConnection.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == checkNetworkConnection.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == checkNetworkConnection.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
}


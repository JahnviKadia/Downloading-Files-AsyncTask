package com.example.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, MyService.class);

        if(intent.getAction().equals("android.intent.action.BATTERY_LOW") ||
                intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED")){
            Toast.makeText(context, "BATTERY IS LOW", Toast.LENGTH_SHORT).show();
            context.stopService(intent1);
        }

         if(intent.getAction().equals("android.intent.action.BATTERY_OKAY") ||
                intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED")){
            Toast.makeText(context, "BATTERY IS OKAY", Toast.LENGTH_SHORT).show();
            intent.setAction("RESUME SERVICE");
            context.startService(intent1);
        }

         if(intent.getAction().equals("dont stop the service")){
            Toast.makeText(context, "DONT STOP SERVICE", Toast.LENGTH_SHORT).show();
            Intent startIntent = new Intent(context.getApplicationContext(), MyService.class);
            context.sendBroadcast(startIntent);
        }
    }
}
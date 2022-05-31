package com.example.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class frag2 extends Fragment {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_frag2, container, false);


        Toast.makeText(getActivity(), "SERVICE START", Toast.LENGTH_SHORT).show();
        if(checkNetworkConnection()) {
            Toast.makeText(getActivity(), "Internet Connected", Toast.LENGTH_SHORT).show();
            getActivity().startService(new Intent(getActivity(), MyService.class));
                }
        else{
            Toast.makeText(getActivity(), "Internet Not Connected", Toast.LENGTH_SHORT).show();
                }

        return v;
    }

    private boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!= null;
    }
}
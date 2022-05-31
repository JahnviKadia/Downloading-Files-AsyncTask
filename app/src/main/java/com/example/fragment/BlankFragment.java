package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BlankFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        v = inflater.inflate(R.layout.fragment_frag2, container, false);
        getActivity().startService(new Intent(getActivity(), MyService.class));
        recyclerView = v.findViewById(R.id.recview);
        return v;
    }
}
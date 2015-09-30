package com.example.ribu.samplenavidationdrawler;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ribu on 08-Sep-15.
 */
public class WhatsHotFragment extends Fragment {
    public WhatsHotFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_whatshot, container, false);

        return rootView;
    }
}

package com.example.ribu.samplenavidationdrawler;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ribu on 08-Sep-15.
 */
public class FindPeopleFragment extends Fragment {
    public FindPeopleFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_findpeople, container, false);

        return rootView;
    }
}

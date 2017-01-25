package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreyaprabhu.ppfcompanion.R;


public class LineGraphFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_line_graph, container, false);

        return rootView;
    }
}

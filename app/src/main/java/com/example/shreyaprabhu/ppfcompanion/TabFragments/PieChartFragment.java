package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreyaprabhu.ppfcompanion.R;

public class PieChartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        return rootView;
    }
}

package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreyaprabhu.ppfcompanion.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        PieChart pieChart = (PieChart)rootView.findViewById(R.id.chart);
        pieChart.animateY(5000);
        // creating data values
        ArrayList<Entry> entries = new ArrayList();
        entries.add(new Entry(ReportFragment.toPieAmountDeposited, 0));
        entries.add(new Entry(ReportFragment.toPieInterest, 1));

        PieDataSet dataset = new PieDataSet(entries," ");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add(getContext().getString(R.string.amount_deposited));
        labels.add(getContext().getString(R.string.interest_gained));

        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setDescription(getContext().getString(R.string.piechart_title));


        return rootView;
    }
}

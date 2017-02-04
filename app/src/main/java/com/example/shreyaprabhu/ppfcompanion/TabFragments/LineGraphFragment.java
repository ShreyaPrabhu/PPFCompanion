package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreyaprabhu.ppfcompanion.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class LineGraphFragment extends Fragment implements
        OnChartGestureListener,
        OnChartValueSelectedListener {

    static ArrayList<Integer> yarrayList;

    private LineChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_line_graph, container, false);
        yarrayList = new ArrayList<>();

        mChart = (LineChart) rootView.findViewById(R.id.linechart);
        mChart.setTouchEnabled(true);
        mChart.setDescription(getContext().getString(R.string.linegraph_title));
        mChart.setNoDataTextDescription(getContext().getString(R.string.provide_data));
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        setData();
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        return rootView;
    }

    /**
     Implement Chart Gesture Listener and Selected Chart Value Listener
     We are going to implement gesture and chart value listener feature in our chart.
     Means, we can zoom in and zoom out our chart and when we will touch to line in the chart, it displays the chart value.
     **/

    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture) {

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex()
                + ", high: " + mChart.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin()
                + ", xmax: " + mChart.getXChartMax()
                + ", ymin: " + mChart.getYChartMin()
                + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }


    public static void setYArrayList(){
        for(int i=0;i<ReportFragment.finalyLineValues.size();i++)
        yarrayList.add(ReportFragment.finalyLineValues.get(i));
    }

    private ArrayList setXaxisList(){
        ArrayList<String> xarrayList = new ArrayList<>();
        for(int i=1;i<=15;i++)
            xarrayList.add(String.valueOf(i));
        return xarrayList;
    }

    public ArrayList<Entry> setYaxisList(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        setYArrayList();
        for(int i=0;i<yarrayList.size();i++){
            yVals.add(new Entry(yarrayList.get(i),i));
        }
        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXaxisList();
        ArrayList<Entry> yVals = setYaxisList();

        LineDataSet set1;
        //Android Library doesnt take string literal from strings.xml
        set1 = new LineDataSet(yVals,getContext().getString(R.string.dataset));
        set1.setFillAlpha(110);

        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);

        LineData data = new LineData(xVals, dataSets);

        mChart.setData(data);

    }

}

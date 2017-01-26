package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shreyaprabhu.ppfcompanion.Activities.PPFReport;
import com.example.shreyaprabhu.ppfcompanion.DataValidationUtils.DateUtils;
import com.example.shreyaprabhu.ppfcompanion.R;


public class ReportFragment extends Fragment {
    private int StartYear;
    private int NoOfYears;
    private int OpeningBalance;
    private int AmountDeposited;
    private int InterestEarned;
    private int ClosingBalance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        PPFReport ppfReport = (PPFReport)getActivity();
        String amountmessage = ppfReport.getIntent().getExtras().getString("amountmessage");
        String startdatemessage = ppfReport.getIntent().getExtras().getString("startdatemessage");
        String ppfmodemessage = ppfReport.getIntent().getExtras().getString("ppfmodemessage");
        Log.v("myData","fragmentData" + amountmessage + " "
                    + startdatemessage + " "
                    + ppfmodemessage + " ");

        setInitialvalues(amountmessage,startdatemessage,ppfmodemessage);

        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        return rootView;
    }

    public void setInitialvalues(String amountmessage, String startdatemessage, String ppfmodemessage ){
        StartYear = Integer.parseInt(startdatemessage);
        NoOfYears = 15;
        AmountDeposited=Integer.parseInt(amountmessage);
        OpeningBalance = 0;
        InterestEarned =(int)(AmountDeposited*0.087);
        ClosingBalance = AmountDeposited+InterestEarned;
    }



}

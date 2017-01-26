package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shreyaprabhu.ppfcompanion.Activities.PPFReport;
import com.example.shreyaprabhu.ppfcompanion.R;
import com.example.shreyaprabhu.ppfcompanion.ReportAdapter.ReportGenerationAdapter;
import com.example.shreyaprabhu.ppfcompanion.ReportAdapter.ReportGenerationModels;

import java.util.ArrayList;


public class ReportFragment extends Fragment {
    private int StartYear;
    private int NoOfYears;
    private int OpeningBalance;
    private int AmountDeposited;
    private int totalInterest;
    private int InterestEarned;
    private int ClosingBalance;

    private ArrayList<ReportGenerationModels> reportGeneration;
    private ReportGenerationModels reportGenerationModels;
    private ReportGenerationAdapter reportGenerationAdapter;
    private RecyclerView recyclerView;
    TextView maturity_year;
    TextView maturity_amount;
    TextView amount_deposited;
    TextView interest_gained;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ppfreport, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        maturity_year = (TextView) rootView.findViewById(R.id.maturity_year_value);
        maturity_amount = (TextView) rootView.findViewById(R.id.maturity_amount_value);
        amount_deposited = (TextView) rootView.findViewById(R.id.amount_deposited_value);
        interest_gained = (TextView) rootView.findViewById(R.id.interest_gained_value);

        PPFReport ppfReport = (PPFReport)getActivity();
        String amountmessage = ppfReport.getIntent().getExtras().getString("amountmessage");
        String startdatemessage = ppfReport.getIntent().getExtras().getString("startdatemessage");
        String ppfmodemessage = ppfReport.getIntent().getExtras().getString("ppfmodemessage");
        Log.v("myData","fragmentData" + amountmessage + " "
                    + startdatemessage + " "
                    + ppfmodemessage + " ");


        reportGeneration = new ArrayList<>();
        reportGenerationAdapter = new ReportGenerationAdapter(getContext(),reportGeneration);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(reportGenerationAdapter);

        setInitialvalues(amountmessage,startdatemessage,ppfmodemessage);

        fixedYearAmountppfCalc(StartYear, ClosingBalance);

        return rootView;
    }



    public void setInitialvalues(String amountmessage, String startdatemessage, String ppfmodemessage ){
        StartYear = Integer.parseInt(startdatemessage);
        NoOfYears = 15;
        AmountDeposited=Integer.parseInt(amountmessage);
        OpeningBalance = 0;
        InterestEarned =(int)(AmountDeposited*0.087);
        totalInterest = InterestEarned;
        ClosingBalance = AmountDeposited+InterestEarned;

        reportGenerationModels = new ReportGenerationModels();
        reportGenerationModels.setStartYear(StartYear);
        reportGenerationModels.setAmountDeposited(AmountDeposited);
        reportGenerationModels.setOpeningBalance(OpeningBalance);
        reportGenerationModels.setInterestEarned(InterestEarned);
        reportGenerationModels.setClosingBalance(ClosingBalance);
        reportGeneration.add(reportGenerationModels);
        reportGenerationAdapter.notifyDataSetChanged();

    }

    public void fixedYearAmountppfCalc(int Year, int CBalance){
        int i = 1;
        while(i!=NoOfYears)
        {

            int OBalance;
            int intermediateResult;
            int interest;
            ReportGenerationModels reportModels = new ReportGenerationModels();

            Year = Year+1;
            reportModels.setStartYear(Year);

            OBalance = CBalance;
            reportModels.setOpeningBalance(OBalance);

            reportModels.setAmountDeposited(AmountDeposited);

            intermediateResult = (CBalance + AmountDeposited);
            interest = (int)(intermediateResult* 0.081);
            totalInterest += interest;
            reportModels.setInterestEarned(interest);

            CBalance = intermediateResult + interest;
            reportModels.setClosingBalance(CBalance);

            reportGeneration.add(reportModels);
            Log.v("myModels","models" + reportModels.getAmountDeposited() + " "
                    + reportModels.getClosingBalance() + " "
                    + reportModels.getInterestEarned() + " "
                    + reportModels.getOpeningBalance() + " "
                    + reportModels.getStartYear() + " ");

            i++;
        }
        reportGenerationAdapter.notifyDataSetChanged();
        maturity_year.setText(String.valueOf(StartYear+NoOfYears));
        maturity_amount.setText(String.valueOf(CBalance));
        amount_deposited.setText(String.valueOf(AmountDeposited*15));
        interest_gained.setText(String.valueOf(totalInterest));

    }


}

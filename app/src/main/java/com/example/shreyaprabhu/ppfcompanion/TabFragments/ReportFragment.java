package com.example.shreyaprabhu.ppfcompanion.TabFragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shreyaprabhu.ppfcompanion.Activities.MyPlan;
import com.example.shreyaprabhu.ppfcompanion.Activities.PPFReport;
import com.example.shreyaprabhu.ppfcompanion.Data.DataContentProvider;
import com.example.shreyaprabhu.ppfcompanion.Data.DataContract;
import com.example.shreyaprabhu.ppfcompanion.R;
import com.example.shreyaprabhu.ppfcompanion.ReportAdapter.ReportGenerationAdapter;
import com.example.shreyaprabhu.ppfcompanion.ReportAdapter.ReportGenerationModels;

import java.util.ArrayList;


public class ReportFragment extends Fragment {
    private int StartYear;
    public static int toPieInterest;
    public static int toPieAmountDeposited;
    private int NoOfYears;
    private int OpeningBalance;
    private int AmountDeposited;
    public int totalInterest = 0;
    private int InterestEarned;
    public int ClosingBalance;
    String ppfmodemessage;

    private ArrayList<ReportGenerationModels> reportGeneration;
    public static ArrayList<Integer>yLineValues;
    private ReportGenerationModels reportGenerationModels;
    private ReportGenerationAdapter reportGenerationAdapter;
    private RecyclerView recyclerView;
    TextView maturity_year;
    TextView maturity_amount;
    TextView amount_deposited;
    TextView interest_gained;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ppfreport, container, false);

        yLineValues = new ArrayList<>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        maturity_year = (TextView) rootView.findViewById(R.id.maturity_year_value);
        maturity_amount = (TextView) rootView.findViewById(R.id.maturity_amount_value);
        amount_deposited = (TextView) rootView.findViewById(R.id.amount_deposited_value);
        interest_gained = (TextView) rootView.findViewById(R.id.interest_gained_value);


        PPFReport ppfReport = (PPFReport)getActivity();
        String amountmessage = ppfReport.getIntent().getExtras().getString("amountmessage");
        String startdatemessage = ppfReport.getIntent().getExtras().getString("startdatemessage");
        ppfmodemessage = ppfReport.getIntent().getExtras().getString("ppfmodemessage");
        Log.v("myData","fragmentData" + amountmessage + " "
                    + startdatemessage + " "
                    + ppfmodemessage + " ");


        reportGeneration = new ArrayList<>();
        reportGenerationAdapter = new ReportGenerationAdapter(getContext(),reportGeneration);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(reportGenerationAdapter);

        assert ppfmodemessage != null;
        if(ppfmodemessage.equals("Fixed Yearly Deposit")){
            setInitialvaluesforfixedYearAmount(amountmessage,startdatemessage);
            fixedYearAmountppfCalc(StartYear, ClosingBalance);
        }
        else{
            setInitialvaluesforfixedMonthlyAmount(amountmessage,startdatemessage);
            fixedMonthlyAmountppfCalc(StartYear, ClosingBalance);
        }


        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onClickAdd();
            }
        });

        return rootView;
    }



    public void setInitialvaluesforfixedYearAmount(String amountmessage, String startdatemessage){
        StartYear = Integer.parseInt(startdatemessage);
        NoOfYears = 15;
        AmountDeposited=Integer.parseInt(amountmessage);
        OpeningBalance = 0;
        InterestEarned =(int)(AmountDeposited*0.081);
        totalInterest += InterestEarned;
        ClosingBalance = AmountDeposited+InterestEarned;
        yLineValues.add(ClosingBalance);

        reportGenerationModels = new ReportGenerationModels();
        reportGenerationModels.setStartYear(StartYear);
        reportGenerationModels.setAmountDeposited(AmountDeposited);
        reportGenerationModels.setOpeningBalance(OpeningBalance);
        reportGenerationModels.setInterestEarned(InterestEarned);
        reportGenerationModels.setClosingBalance(ClosingBalance);
        reportGeneration.add(reportGenerationModels);
        reportGenerationAdapter.notifyDataSetChanged();

    }

    public void setInitialvaluesforfixedMonthlyAmount(String amountmessage, String startdatemessage){
        StartYear = Integer.parseInt(startdatemessage);
        NoOfYears = 15;
        AmountDeposited =Integer.parseInt(amountmessage);
        Log.v("myAmount","amountdepsited"+AmountDeposited);
        OpeningBalance = 0;
        InterestEarned = getCompoundedInterestforOneYear(AmountDeposited,AmountDeposited);
        totalInterest += InterestEarned;
        ClosingBalance = (AmountDeposited*12)+InterestEarned;
        yLineValues.add(ClosingBalance);

        reportGenerationModels = new ReportGenerationModels();
        reportGenerationModels.setStartYear(StartYear);
        reportGenerationModels.setAmountDeposited(AmountDeposited*12);
        reportGenerationModels.setOpeningBalance(OpeningBalance);
        reportGenerationModels.setInterestEarned(InterestEarned);
        reportGenerationModels.setClosingBalance(ClosingBalance);
        reportGeneration.add(reportGenerationModels);
        reportGenerationAdapter.notifyDataSetChanged();

    }

    public int getCompoundedInterestforOneYear(int amountDeposited,int ad){
        int i = 0;
        double interest = 0;
        while(i<12){

            interest += amountDeposited*(0.081/12);
            amountDeposited += ad;
            i++;

        }
        return (int)interest;
    }


    public void fixedMonthlyAmountppfCalc(int Year, int CBalance){
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

            reportModels.setAmountDeposited(AmountDeposited*12);

            intermediateResult = (CBalance + (AmountDeposited));
            interest = getCompoundedInterestforOneYear(intermediateResult,AmountDeposited);
            totalInterest += interest;
            reportModels.setInterestEarned(interest);

            CBalance += (AmountDeposited*12) + interest;
            yLineValues.add(CBalance);
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
        amount_deposited.setText(String.valueOf(AmountDeposited*15*12));
        interest_gained.setText(String.valueOf(totalInterest));
        toPieInterest = totalInterest;
        toPieAmountDeposited = AmountDeposited*15*12;

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
            yLineValues.add(CBalance);
            reportModels.setClosingBalance(CBalance);

            reportGeneration.add(reportModels);
            Log.v("myModels","models" + reportModels.getAmountDeposited() + " "
                    + reportModels.getClosingBalance() + " "
                    + reportModels.getInterestEarned() + " "
                    + reportModels.getOpeningBalance() + " "
                    + reportModels.getStartYear() + " ");

            i++;
        }
        ClosingBalance = CBalance;
        reportGenerationAdapter.notifyDataSetChanged();
        maturity_year.setText(String.valueOf(StartYear+NoOfYears));
        maturity_amount.setText(String.valueOf(ClosingBalance));
        amount_deposited.setText(String.valueOf(AmountDeposited*15));
        interest_gained.setText(String.valueOf(totalInterest));
        toPieInterest = totalInterest;
        toPieAmountDeposited = AmountDeposited*15;
    }


    public void onClickAdd() {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(DataContract.PPFEntry.COLUMN_STARTYEAR, StartYear);
        values.put(DataContract.PPFEntry.COLUMN_PPF_MODE, ppfmodemessage);
        values.put(DataContract.PPFEntry.COLUMN_AMOUNT_DEPOSITED,AmountDeposited);
        values.put(DataContract.PPFEntry.COLUMN_MATURITY_YEAR,maturity_year.getText().toString());
        values.put(DataContract.PPFEntry.COLUMN_MATURITY_AMOUNT,maturity_amount.getText().toString());
        values.put(DataContract.PPFEntry.COLUMN_TOTAL_AMOUNT_DEPOSITED,toPieAmountDeposited);
        values.put(DataContract.PPFEntry.COLUMN_TOTAL_INTEREST_GAINED,toPieInterest);

        Uri uri = getContext().getContentResolver().insert(
                   DataContract.PPFEntry.CONTENT_URI, values);

        assert uri != null;
        Toast.makeText(getContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }


}

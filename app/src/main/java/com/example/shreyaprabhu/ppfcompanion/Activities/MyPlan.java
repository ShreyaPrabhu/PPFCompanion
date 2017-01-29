package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shreyaprabhu.ppfcompanion.Data.DataContract;
import com.example.shreyaprabhu.ppfcompanion.PlanAdapter.MyPlanAdapter;
import com.example.shreyaprabhu.ppfcompanion.PlanAdapter.MyPlanModels;
import com.example.shreyaprabhu.ppfcompanion.R;

import java.util.ArrayList;

public class MyPlan extends AppCompatActivity {

    private ArrayList<MyPlanModels> myPlan;
    private MyPlanAdapter myPlanAdapter;
    private RecyclerView my_plan_recyclerView;
    private Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refresh = (Button) findViewById(R.id.my_plan_refresh);

        my_plan_recyclerView= (RecyclerView) findViewById(R.id.my_plan_recyclerview);

        myPlan = new ArrayList<>();
        myPlanAdapter = new MyPlanAdapter(this,myPlan);
        my_plan_recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        my_plan_recyclerView.setAdapter(myPlanAdapter);

        onClickRetrieveStudents();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
    }

    public void onClickRetrieveStudents() {
        // Retrieve student records
        String URL = "content://com.example.shreyaprabhu.ppfcompanion";

        Uri plans = Uri.parse(URL);
        Cursor c =managedQuery(plans, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PLAN_ID)) +
                                ", " +  c.getString(c.getColumnIndex(DataContract.PPFEntry.COLUMN_AMOUNT_DEPOSITED)) +
                                ", " + c.getString(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PPF_MODE)),
                        Toast.LENGTH_SHORT).show();
                MyPlanModels myPlanModels = new MyPlanModels();
                myPlanModels.setId(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PLAN_ID)));
                myPlanModels.setStartYear(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_STARTYEAR)));
                myPlanModels.setPpfModeMessage(c.getString(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PPF_MODE)));
                myPlanModels.setAmountDeposited(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_AMOUNT_DEPOSITED)));
                myPlanModels.setMaturityYear(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_YEAR)));
                myPlanModels.setMaturityAmount(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_AMOUNT)));
                myPlanModels.setTotalAmountDeposited(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_TOTAL_AMOUNT_DEPOSITED)));
                myPlanModels.setTotalInterestGained(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_TOTAL_INTEREST_GAINED)));
                myPlan.add(myPlanModels);
                myPlanAdapter.notifyDataSetChanged();
            } while (c.moveToNext());
        }
    }

}

package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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

public class MyPlan extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private ArrayList<MyPlanModels> myPlan;
    private MyPlanAdapter myPlanAdapter;
    private RecyclerView my_plan_recyclerView;
    private static final int ID_PLAN_LOADER = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        my_plan_recyclerView= (RecyclerView) findViewById(R.id.my_plan_recyclerview);

        myPlan = new ArrayList<>();
        myPlanAdapter = new MyPlanAdapter(this,myPlan);
        my_plan_recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        my_plan_recyclerView.setAdapter(myPlanAdapter);

        getSupportLoaderManager().initLoader(ID_PLAN_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                this,
                DataContract.PPFEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do{

                    MyPlanModels myPlanModels = new MyPlanModels();
                    myPlanModels.setId(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_PLAN_ID)));
                    myPlanModels.setStartYear(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_STARTYEAR)));
                    myPlanModels.setPpfModeMessage(cursor.getString(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_PPF_MODE)));
                    myPlanModels.setAmountDeposited(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_AMOUNT_DEPOSITED)));
                    myPlanModels.setMaturityYear(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_YEAR)));
                    myPlanModels.setMaturityAmount(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_AMOUNT)));
                    myPlanModels.setTotalAmountDeposited(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_TOTAL_AMOUNT_DEPOSITED)));
                    myPlanModels.setTotalInterestGained(cursor.getInt(cursor.getColumnIndex(DataContract.PPFEntry.COLUMN_TOTAL_INTEREST_GAINED)));
                    myPlan.add(myPlanModels);
                    myPlanAdapter.notifyDataSetChanged();
                } while (cursor.moveToNext());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

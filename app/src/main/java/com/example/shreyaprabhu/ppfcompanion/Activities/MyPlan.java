package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.shreyaprabhu.ppfcompanion.Data.DataContract;
import com.example.shreyaprabhu.ppfcompanion.R;

public class MyPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRetrieveStudents();
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
            } while (c.moveToNext());
        }
    }

}

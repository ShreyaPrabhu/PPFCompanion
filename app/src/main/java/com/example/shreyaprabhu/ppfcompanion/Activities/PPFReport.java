package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.shreyaprabhu.ppfcompanion.ReportAdapter.ReportGenerationModels;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.LineGraphFragment;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.PieChartFragment;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.ReportFragment;
import com.example.shreyaprabhu.ppfcompanion.TabUtils.TabLayoutListener;
import com.example.shreyaprabhu.ppfcompanion.TabUtils.ViewPagerAdapter;
import com.example.shreyaprabhu.ppfcompanion.R;

public class PPFReport extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppfreport);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        TabLayoutListener.tabListener(this,tabLayout,mViewPager);


    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ReportFragment(),getApplicationContext().getString(R.string.report));
        adapter.addFrag(new LineGraphFragment(),getApplicationContext().getString(R.string.linegraph));
        adapter.addFrag(new PieChartFragment(),getApplicationContext().getString(R.string.piechart));
        viewPager.setAdapter(adapter);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ppfreport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.my_plans) {
            Intent intent = new Intent(this,MyPlan.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}

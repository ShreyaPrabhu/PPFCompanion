package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ReportFragment(),"REPORT");
        adapter.addFrag(new LineGraphFragment(),"LINE GRAPH");
        adapter.addFrag(new PieChartFragment(),"PIE CHART");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

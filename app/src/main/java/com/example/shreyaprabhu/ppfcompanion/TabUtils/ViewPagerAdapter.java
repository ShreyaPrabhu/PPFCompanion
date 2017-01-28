package com.example.shreyaprabhu.ppfcompanion.TabUtils;



import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.shreyaprabhu.ppfcompanion.TabFragments.LineGraphFragment;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.PieChartFragment;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.ReportFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreya Prabhu on 25-01-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ReportFragment();
            case 1:
                return new LineGraphFragment();
            case 2:
                return new PieChartFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
package com.example.shreyaprabhu.ppfcompanion.TabUtils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


/**
 * Created by Shreya Prabhu on 25-01-2017.
 */

public class TabLayoutListener {

    static Context mContext;

    public static void tabListener(Context context, TabLayout tabLayout, final ViewPager viewPager) {
        mContext = context;
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}

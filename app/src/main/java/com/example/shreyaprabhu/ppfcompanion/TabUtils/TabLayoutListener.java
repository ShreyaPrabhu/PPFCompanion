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
                        Snackbar.make(viewPager, "REPORT", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 1:
                        Snackbar.make(viewPager, "LINE GRAPH", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 2:
                        Snackbar.make(viewPager, "PIE CHART", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
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

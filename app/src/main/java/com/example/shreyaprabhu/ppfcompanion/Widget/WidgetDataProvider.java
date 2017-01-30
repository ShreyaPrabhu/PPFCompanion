package com.example.shreyaprabhu.ppfcompanion.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.example.shreyaprabhu.ppfcompanion.Data.DataContract;
import com.example.shreyaprabhu.ppfcompanion.R;

import java.util.ArrayList;

/**
 * Created by Shreya Prabhu on 30-01-2017.
 */


public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<WidgetModel> widgetModels;

    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        widgetModels = new ArrayList();
    }

    @Override
    public int getCount() {
        return widgetModels.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_item);
            mView.setTextViewText(R.id.MyPlan,
                    String.valueOf(widgetModels.get(position).getId()));
            mView.setTextViewText(R.id.StartYear,
                    String.valueOf(widgetModels.get(position).getStartYear()));
            mView.setTextViewText(R.id.PpfModeMessage,
                    String.valueOf(widgetModels.get(position).getPpfModeMessage()));
            mView.setTextViewText(R.id.MaturityYear,
                    String.valueOf(widgetModels.get(position).getMaturityYear()));
            mView.setTextViewText(R.id.MaturityAmount,
                    String.valueOf(widgetModels.get(position).getMaturityAmount()));
        return mView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
       initData();

    }

    private void initData() {
        widgetModels.clear();
        String URL = "content://com.example.shreyaprabhu.ppfcompanion";

        Uri plans = Uri.parse(URL);
        Cursor c  = mContext.getContentResolver().query(plans, null, null, null, null);

        if (c!=null && c.moveToFirst()) {
            do{

                WidgetModel widgetModel = new WidgetModel();
                widgetModel.setId(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PLAN_ID)));
                widgetModel.setStartYear(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_STARTYEAR)));
                widgetModel.setPpfModeMessage(c.getString(c.getColumnIndex(DataContract.PPFEntry.COLUMN_PPF_MODE)));
                widgetModel.setMaturityYear(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_YEAR)));
                widgetModel.setMaturityAmount(c.getInt(c.getColumnIndex(DataContract.PPFEntry.COLUMN_MATURITY_AMOUNT)));
                widgetModels.add(widgetModel);
            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onDestroy() {

    }

}

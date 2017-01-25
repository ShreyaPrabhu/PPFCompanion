package com.example.shreyaprabhu.ppfcompanion.ReportAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shreya Prabhu on 25-01-2017.
 */

class ReportGenerationAdapter extends RecyclerView.Adapter<ReportGenerationAdapter.RowViewHolder>{


    @Override
    public ReportGenerationAdapter.RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ReportGenerationAdapter.RowViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RowViewHolder extends RecyclerView.ViewHolder {


        RowViewHolder(View itemView) {
            super(itemView);


        }
    }
}

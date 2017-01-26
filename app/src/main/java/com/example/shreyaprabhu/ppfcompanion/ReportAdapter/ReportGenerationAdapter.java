package com.example.shreyaprabhu.ppfcompanion.ReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shreyaprabhu.ppfcompanion.R;

import java.util.ArrayList;

/**
 * Created by Shreya Prabhu on 25-01-2017.
 */

public class ReportGenerationAdapter extends RecyclerView.Adapter<ReportGenerationAdapter.RowViewHolder>{

    Context context;
    private ArrayList<ReportGenerationModels> reportGeneration = new ArrayList<>();

    public ReportGenerationAdapter(Context context, ArrayList<ReportGenerationModels> reportGeneration) {
        super();
        this.context = context;
        this.reportGeneration = reportGeneration;
    }


    @Override
    public ReportGenerationAdapter.RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.unit_report_item, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportGenerationAdapter.RowViewHolder holder, int position) {
        ReportGenerationModels reportGenerationModels = reportGeneration.get(position);
        holder.StartYear.setText(String.valueOf(reportGenerationModels.getStartYear()));
        holder.OpeningBalance.setText(String.valueOf(reportGenerationModels.getOpeningBalance()));
        holder.AmountDeposited.setText(String.valueOf(reportGenerationModels.getAmountDeposited()));
        holder.InterestEarned.setText(String.valueOf(reportGenerationModels.getInterestEarned()));
        holder.ClosingBalance.setText(String.valueOf(reportGenerationModels.getClosingBalance()));
    }

    @Override
    public int getItemCount() {
        return reportGeneration.size();
    }

    class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView StartYear;
        private TextView OpeningBalance;
        private TextView AmountDeposited;
        private TextView InterestEarned;
        private TextView ClosingBalance;

        RowViewHolder(View itemView) {
            super(itemView);
            StartYear = (TextView) itemView.findViewById(R.id.StartYear);
            OpeningBalance = (TextView) itemView.findViewById(R.id.OpeningBalance);
            AmountDeposited = (TextView) itemView.findViewById(R.id.AmountDeposited);
            InterestEarned = (TextView) itemView.findViewById(R.id.InterestEarned);
            ClosingBalance = (TextView) itemView.findViewById(R.id.ClosingBalance);

        }
    }
}

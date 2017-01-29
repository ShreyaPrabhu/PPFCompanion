package com.example.shreyaprabhu.ppfcompanion.PlanAdapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shreyaprabhu.ppfcompanion.Data.DataContract;
import com.example.shreyaprabhu.ppfcompanion.R;


import java.util.ArrayList;

/**
 * Created by Shreya Prabhu on 29-01-2017.
 */

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.RowViewHolder>{

    Context context;
    ArrayList<MyPlanModels> myPlan = new ArrayList<>();
    MyPlanAdapter myPlanAdapter;

    public MyPlanAdapter(Context context, ArrayList<MyPlanModels> myPlan) {
        super();
        this.context = context;
        this.myPlan = myPlan;
        this.myPlanAdapter = this;
    }

    @Override
    public MyPlanAdapter.RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_plan_item, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyPlanAdapter.RowViewHolder holder, final int position) {
        final MyPlanModels myPlanModels = myPlan.get(position);
        holder.StartYear.setText(String.valueOf(myPlanModels.getStartYear()));
        holder.PpfModeMessage.setText(String.valueOf(myPlanModels.getPpfModeMessage()));
        holder.AmountDeposited.setText(String.valueOf(myPlanModels.getAmountDeposited()));
        holder.MaturityYear.setText(String.valueOf(myPlanModels.getMaturityYear()));
        holder.MaturityAmount.setText(String.valueOf(myPlanModels.getMaturityAmount()));
        holder.TotalAmountDeposited.setText(String.valueOf(myPlanModels.getTotalAmountDeposited()));
        holder.TotalInterestGained.setText(String.valueOf(myPlanModels.getTotalInterestGained()));

        holder.DeleteItem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Uri uri = DataContract.PPFEntry.buildPPfDataUriWithPlanID(myPlanModels.getId());
                            ContentResolver cr = context.getContentResolver();
                            cr.delete(uri,null,null);
                            myPlan.remove(position);
                            myPlanAdapter.notifyDataSetChanged();

                    }
                }

        );

    }


    @Override
    public int getItemCount() {
        return myPlan.size();
    }


    class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView StartYear;
        private TextView PpfModeMessage;
        private TextView AmountDeposited;
        private TextView MaturityYear;
        private TextView MaturityAmount;

        private TextView TotalAmountDeposited;
        private TextView TotalInterestGained;
        private TextView DeleteItem;

        RowViewHolder(View itemView) {
            super(itemView);
            StartYear = (TextView) itemView.findViewById(R.id.StartYear);
            PpfModeMessage = (TextView) itemView.findViewById(R.id.PpfModeMessage);
            AmountDeposited = (TextView) itemView.findViewById(R.id.AmountDeposited);
            MaturityYear = (TextView) itemView.findViewById(R.id.MaturityYear);
            MaturityAmount = (TextView) itemView.findViewById(R.id.MaturityAmount);
            TotalAmountDeposited = (TextView) itemView.findViewById(R.id.TotalAmountDeposited);
            TotalInterestGained = (TextView) itemView.findViewById(R.id.TotalInterestGained);
            DeleteItem = (TextView) itemView.findViewById(R.id.Delete);


        }
    }
}

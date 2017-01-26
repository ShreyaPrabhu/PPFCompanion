package com.example.shreyaprabhu.ppfcompanion.ReportAdapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Shreya Prabhu on 25-01-2017.
 */

public class ReportGenerationModels implements Parcelable {

    private int StartYear;
    private int OpeningBalance;
    private int AmountDeposited;
    private int InterestEarned;
    private int ClosingBalance;

    public ReportGenerationModels(int StartYear,int OpeningBalance, int AmountDeposited, int InterestEarned,int ClosingBalance){
        this.StartYear = StartYear;
        this.OpeningBalance = OpeningBalance;
        this.AmountDeposited = AmountDeposited;
        this.InterestEarned = InterestEarned;
        this.ClosingBalance = ClosingBalance;
    }

    public ReportGenerationModels(){

    }

    public void setStartYear(int StartYear){
        this.StartYear = StartYear;
    }

    public int getStartYear(){
        return StartYear;
    }

    public void setAmountDeposited(int AmountDeposited){
        this.AmountDeposited = AmountDeposited;
    }

    public int getAmountDeposited(){
        return AmountDeposited;
    }

    public void setOpeningBalance(int OpeningBalance){
        this.OpeningBalance = OpeningBalance;
    }

    public int getOpeningBalance(){
        return OpeningBalance;
    }

    public void setInterestEarned(int InterestEarned){
        this.InterestEarned = InterestEarned;
    }

    public int getInterestEarned(){
        return InterestEarned;
    }

    public void setClosingBalance(int ClosingBalance){
        this.ClosingBalance = ClosingBalance;
    }

    public int getClosingBalance(){
        return ClosingBalance;
    }

    public static final Creator<ReportGenerationModels> CREATOR = new Creator<ReportGenerationModels>() {
        @Override
        public ReportGenerationModels createFromParcel(Parcel in) {
            return new ReportGenerationModels(in);
        }

        @Override
        public ReportGenerationModels[] newArray(int size) {
            return new ReportGenerationModels[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(StartYear);
        parcel.writeInt(OpeningBalance);
        parcel.writeInt(AmountDeposited);
        parcel.writeInt(InterestEarned);
        parcel.writeInt(ClosingBalance);
    }

    private ReportGenerationModels(Parcel in) {
        StartYear = in.readInt();
        OpeningBalance = in.readInt();
        AmountDeposited = in.readInt();
        InterestEarned = in.readInt();
        ClosingBalance = in.readInt();
    }
}

package com.example.shreyaprabhu.ppfcompanion.PlanAdapter;

import java.io.Serializable;

/**
 * Created by Shreya Prabhu on 29-01-2017.
 */

public class MyPlanModels implements Serializable {

    private int Id;
    private int StartYear;
    private String PpfModeMessage;
    private int AmountDeposited;
    private int MaturityYear;
    private int MaturityAmount;
    private int TotalAmountDeposited;
    private int TotalInterestGained;

    public MyPlanModels(){

    }

    public MyPlanModels(int StartYear,String PpfModeMessage,int AmountDeposited,int MaturityYear,
                        int MaturityAmount,int TotalAmountDeposited,int TotalInterestGained){
        this.StartYear = StartYear;
        this.PpfModeMessage = PpfModeMessage;
        this.AmountDeposited = AmountDeposited;
        this.MaturityYear = MaturityYear;
        this.MaturityAmount = MaturityAmount;
        this.TotalAmountDeposited = TotalAmountDeposited;
        this.TotalInterestGained = TotalInterestGained;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStartYear() {
        return StartYear;
    }

    public void setStartYear(int startYear) {
        StartYear = startYear;
    }

    public String getPpfModeMessage() {
        return PpfModeMessage;
    }

    public int getAmountDeposited() {
        return AmountDeposited;
    }

    public void setAmountDeposited(int amountDeposited) {
        AmountDeposited = amountDeposited;
    }

    public void setPpfModeMessage(String ppfModeMessage) {
        PpfModeMessage = ppfModeMessage;
    }

    public int getMaturityYear() {
        return MaturityYear;
    }

    public void setMaturityYear(int maturityYear) {
        MaturityYear = maturityYear;
    }

    public int getMaturityAmount() {
        return MaturityAmount;
    }

    public void setMaturityAmount(int maturityAmount) {
        MaturityAmount = maturityAmount;
    }

    public int getTotalAmountDeposited() {
        return TotalAmountDeposited;
    }

    public void setTotalAmountDeposited(int totalAmountDeposited) {
        TotalAmountDeposited = totalAmountDeposited;
    }

    public int getTotalInterestGained() {
        return TotalInterestGained;
    }

    public void setTotalInterestGained(int totalInterestGained) {
        TotalInterestGained = totalInterestGained;
    }
}


package com.example.shreyaprabhu.ppfcompanion.Widget;

/**
 * Created by Shreya Prabhu on 30-01-2017.
 */

public class WidgetModel {

    private int Id;
    private int StartYear;
    private String PpfModeMessage;
    private int MaturityYear;
    private int MaturityAmount;

    public WidgetModel(){

    }

    public WidgetModel(int Id, int StartYear,String PpfModeMessage,int MaturityYear,
                        int MaturityAmount){
        this.Id = Id;
        this.StartYear = StartYear;
        this.PpfModeMessage = PpfModeMessage;
        this.MaturityYear = MaturityYear;
        this.MaturityAmount = MaturityAmount;
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

}

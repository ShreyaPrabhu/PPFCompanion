package com.example.shreyaprabhu.ppfcompanion.DataValidationUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Shreya Prabhu on 24-01-2017.
 */

public class ValidateEntry {

    static ValidateEntry validateEntry = new ValidateEntry();
    private static Context mContext;


    private boolean nullCheck(String amount, String no_of_years, int day, int month, int year, String startDate){
        boolean x = TextUtils.isEmpty(amount) || TextUtils.isEmpty(no_of_years) || day==0 || month==0 || year==0 || TextUtils.isEmpty(startDate);
        Log.v("my","x" + x);
        if(x){
            CreateDialogBox.alertDialog(mContext,"Please enter all the details");
            return true;
        }
        return false;
    }



    //check for amount
    private boolean isAmountValid(String amount) {
        if(Integer.parseInt(amount) >= 500){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,"Amount Deposite should be greater than 500");
            return false;
        }

    }

    private boolean isNoOfYearsValid(String no_of_years){
        if(Integer.parseInt(no_of_years) >= 15){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,"No of Years should be 15 years or more");
            return false;
        }

    }


    private boolean isStartDateValid(String startDate) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int day= calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        StringBuilder date_string = DateUtils.showDate(day,month,year);
        if(DateUtils.compareDates(date_string.toString(),startDate)){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,"Start Date cannot be before present Date " + date_string.toString() );
            return false;
        }

    }

    public boolean isDateValid(int day, int month, int year){
        StringBuilder day_string = DateUtils.showDate(day,month,year);
        if(DateUtils.isThisDateValid(day_string.toString())){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,"Date " +day_string.toString()+ " is not valid date" );
            return false;
        }

    }

    public static boolean validate(Context context, String amount, String no_of_years, int day, int month, int year, String startDate) throws ParseException {
        mContext = context;
        if(!validateEntry.nullCheck(amount,no_of_years,day,month,year,startDate)){
            return(validateEntry.isAmountValid(amount) && (validateEntry.isNoOfYearsValid(no_of_years))
                    && (validateEntry.isDateValid(day,month,year)) && (validateEntry.isStartDateValid(startDate)));

        }
        return false;
    }

}


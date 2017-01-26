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


    private boolean nullCheck(String amount,String year){
        boolean x = TextUtils.isEmpty(amount) || TextUtils.isEmpty(year);
        Log.v("my","x" + x);
        if(x){
            CreateDialogBox.alertDialog(mContext,"Please enter all the details");
            return true;
        }
        return false;
    }

    private boolean isAmountValid(String amount) {
        if(Integer.parseInt(amount) >= 500){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,"Amount Deposite should be greater than 500");
            return false;
        }

    }


    private boolean isStartYearValid(String startYear) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return Integer.parseInt(startYear) >= year;
    }


    public static boolean validate(Context context, String amount, String year) throws ParseException {
        mContext = context;
        if(!validateEntry.nullCheck(amount,year)){
            return(validateEntry.isAmountValid(amount)
                     && (validateEntry.isStartYearValid(year)));

        }
        return false;
    }

}


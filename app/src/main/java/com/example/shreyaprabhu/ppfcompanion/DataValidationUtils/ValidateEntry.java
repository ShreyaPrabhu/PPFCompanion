package com.example.shreyaprabhu.ppfcompanion.DataValidationUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.shreyaprabhu.ppfcompanion.R;

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
            CreateDialogBox.alertDialog(mContext, mContext.getString(R.string.enter_details));
            return true;
        }
        return false;
    }

    private boolean isAmountValid(String amount) {
        if(Integer.parseInt(amount) >= 500){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,mContext.getString(R.string.amount_validity));
            return false;
        }

    }


    private boolean isStartYearValid(String startYear) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if((Integer.parseInt(startYear) >= year)&&(Integer.parseInt(startYear)<2040)){
            return true;
        }
        else{
            CreateDialogBox.alertDialog(mContext,mContext.getString(R.string.year_validity) + year + mContext.getString(R.string.and) + 2040);
            return false;
        }
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


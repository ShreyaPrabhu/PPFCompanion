package com.example.shreyaprabhu.ppfcompanion.DataValidationUtils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shreya Prabhu on 24-01-2017.
 */

public class DateUtils {

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static StringBuilder showDate(int year) {
        StringBuilder date_string = new StringBuilder().append("1")
                .append("-").append("4").append("-").append(year);
        return date_string;

    }

    //TODO : Fix code to successfully pass all the test

    public static int isThisDateValid(String dateToValidate){

        //0 - null or wrong date
        //1 - wrong month
        //2 - correct

        if(dateToValidate == null){
            return 0;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            Log.v("myTag","myValidDate " + date);
            int month = returnmonthfromString(dateToValidate);
            if(month == 4){
                return 2;
            }
            else
                return 1;

        } catch (ParseException e) {

            e.printStackTrace();
            return 0;
        }
    }

    public static boolean compareDates(String presentDate, String enteredDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        Date date1 = sdf.parse(presentDate);
        Date date2 = sdf.parse(enteredDate);

        Log.v("myTag","date1 : " + sdf.format(date1));
        Log.v("myTag","date2 : " + sdf.format(date2));

        if (date1.after(date2)) {
            return false;
        }

        else if (date1.before(date2)) {
            return true;
        }

        else {
            return true;
        }

    }

    public int returndayfromString(String dateString){
        String str[] = dateString.split("-");
        int day = Integer.parseInt(str[0]);
        return day;
    }

    public static int returnmonthfromString(String dateString){
        String str[] = dateString.split("-");
        return Integer.parseInt(str[1]);
    }

    public static int returnyearfromString(String dateString){
        String str[] = dateString.split("-");
        return Integer.parseInt(str[2].trim());
    }
}

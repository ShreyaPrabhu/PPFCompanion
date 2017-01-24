package com.example.shreyaprabhu.ppfcompanion.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP on 24-01-2017.
 */

public class DateUtils {

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static StringBuilder showDate(int day, int month, int year) {
        StringBuilder date_string = new StringBuilder().append(day)
                .append("-").append(month + 1).append("-").append(year)
                .append(" ");
        return date_string;

    }

    //TODO : Fix code to successfully pass all the test

    public static boolean isThisDateValid(String dateToValidate){


        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            //Log.v("myTag","myValidDate " + date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
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
}

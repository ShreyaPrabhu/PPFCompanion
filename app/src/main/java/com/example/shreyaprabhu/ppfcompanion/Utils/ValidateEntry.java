package com.example.shreyaprabhu.ppfcompanion.Utils;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by HP on 24-01-2017.
 */

public class ValidateEntry {

    static ValidateEntry validateEntry = new ValidateEntry();

    public boolean nullCheck(String amount, String no_of_years, int day, int month, int year, String startDate){
        return (amount==null || no_of_years==null || day==0 || month==0 || year==0 || startDate==null);
    }

    //check for amount
    public boolean isAmountValid(String amount) {
        return Integer.parseInt(amount) >= 500;
    }

    public boolean isNoOfYearsValid(String no_of_years){
        return Integer.parseInt(no_of_years) >= 15;
    }

    public boolean isDateValid(int day, int month, int year){
        StringBuilder day_string = DateUtils.showDate(day,month,year);
        return DateUtils.isThisDateValid(day_string.toString());
    }

    public boolean isStartDateValid(String startDate) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int day= calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        StringBuilder date_string = DateUtils.showDate(day,month,year);
        return DateUtils.compareDates(date_string.toString(),startDate);
    }

    public static boolean validate(String amount, String no_of_years, int day, int month, int year, String startDate) throws ParseException {
        return(!validateEntry.nullCheck(amount,no_of_years,day,month,year,startDate)
            && validateEntry.isAmountValid(amount) && (validateEntry.isNoOfYearsValid(no_of_years))
                && (validateEntry.isDateValid(day,month,year)) && (validateEntry.isStartDateValid(startDate)));

    }
}

package com.example.shreyaprabhu.ppfcompanion.Data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.security.PublicKey;

/**
 * Created by Shreya Prabhu on 28-01-2017.
 */

public class DataContract {

    public static final String CONTENT_AUTHORITY = "com.example.shreyaprabhu.ppfcompanion";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PPFDATA = "ppfdata";

    public static final class PPFEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_PPFDATA)
                .build();

        public static final String TABLE_NAME = "ppfInfo";

        public static final String COLUMN_PLAN_ID = "plan_id";
        public static final String COLUMN_STARTYEAR = "startyear";
        public static final String COLUMN_PPF_MODE = "ppfmode";
        public static final String COLUMN_AMOUNT_DEPOSITED = "amountdeposited";
        public static final String COLUMN_MATURITY_YEAR = "maturityyear";
        public static final String COLUMN_MATURITY_AMOUNT = "maturityamount";
        public static final String COLUMN_TOTAL_AMOUNT_DEPOSITED = "totalamountdeposited";
        public static final String COLUMN_TOTAL_INTEREST_GAINED = "totalinterestgained";


        public static Uri buildPPfDataUriWithPlanID(int id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }
    }
}

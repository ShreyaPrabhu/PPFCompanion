package com.example.shreyaprabhu.ppfcompanion.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shreya Prabhu on 28-01-2017.
 */

public class DataDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ppfInfo.db";
    private static final int DATABASE_VERSION = 1;

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_DB_TABLE =
                " CREATE TABLE " + DataContract.PPFEntry.TABLE_NAME + " (" +
                        DataContract.PPFEntry.COLUMN_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DataContract.PPFEntry.COLUMN_STARTYEAR + " INTEGER NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_PPF_MODE + " TEXT NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_AMOUNT_DEPOSITED + " INTEGER NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_MATURITY_YEAR + " INTEGER NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_MATURITY_AMOUNT +" INTEGER NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_TOTAL_AMOUNT_DEPOSITED + " INTEGER NOT NULL, " +
                        DataContract.PPFEntry.COLUMN_TOTAL_INTEREST_GAINED + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataContract.PPFEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

package com.example.shreyaprabhu.ppfcompanion.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import static com.example.shreyaprabhu.ppfcompanion.Data.DataContract.PPFEntry.CONTENT_URI;

/**
 * Created by Shreya Prabhu on 28-01-2017.
 */

public class DataContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DataDbHelper mOpenHelper;

    static final int PLANS = 1;
    static final int PLAN_ID = 2;

    public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,DataContract.PATH_PPFDATA,PLANS);
        matcher.addURI(authority, DataContract.PATH_PPFDATA,PLAN_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DataDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DataContract.PPFEntry.TABLE_NAME);
        switch (sUriMatcher.match(uri)){
            case PLANS :
                break;
            case PLAN_ID :
                qb.appendWhere( DataContract.PPFEntry.COLUMN_PLAN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                break;
        }


            sortOrder = DataContract.PPFEntry.COLUMN_PLAN_ID;


        Cursor c = qb.query(mOpenHelper.getWritableDatabase(),	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        long rowID = db.insert(DataContract.PPFEntry.TABLE_NAME, null , contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int numRowsDeleted;
        switch (sUriMatcher.match(uri)){
            case PLANS:
                numRowsDeleted = mOpenHelper.getWritableDatabase().delete(DataContract.PPFEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PLAN_ID:
                String id = uri.getPathSegments().get(1);
                numRowsDeleted = mOpenHelper.getWritableDatabase().delete(
                        DataContract.PPFEntry.TABLE_NAME,
                        DataContract.PPFEntry.COLUMN_PLAN_ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return numRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case PLANS:
                count = mOpenHelper.getWritableDatabase().update(DataContract.PPFEntry.TABLE_NAME, contentValues, selection, selectionArgs);
                break;

            case PLAN_ID:
                count = mOpenHelper.getWritableDatabase().update(
                        DataContract.PPFEntry.TABLE_NAME, contentValues,
                        DataContract.PPFEntry.COLUMN_PLAN_ID + " = " + uri.getLastPathSegment() +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

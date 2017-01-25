package com.example.shreyaprabhu.ppfcompanion.DataValidationUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Shreya Prabhu on 24-01-2017.
 */

public class CreateDialogBox {

    static void alertDialog(Context mContext, String alertMessage){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder.setMessage(alertMessage);
        final AlertDialog alert = alertDialogBuilder.create();
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }


                });

        alert.show();
    }

}

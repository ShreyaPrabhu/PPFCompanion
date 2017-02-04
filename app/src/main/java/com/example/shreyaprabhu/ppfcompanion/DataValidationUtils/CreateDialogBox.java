package com.example.shreyaprabhu.ppfcompanion.DataValidationUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shreyaprabhu.ppfcompanion.PlanAdapter.MyPlanAdapter;
import com.example.shreyaprabhu.ppfcompanion.R;

import org.w3c.dom.Text;

/**
 * Created by Shreya Prabhu on 24-01-2017.
 */

public class CreateDialogBox {


    public static void alertDialog(Context mContext, String alertMessage){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(R.string.AlertDialogTitle);
        alertDialogBuilder.setMessage(alertMessage);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}

package com.example.sarah.prospectsproject.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sarah on 2/09/2018.
 */

public class CustomAlertDialog {

    private final Activity activity;

    public CustomAlertDialog(Activity activity) {
        this.activity = activity;
    }


    public void showAlertDialog(int title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, View view) {
        showAlertDialog(activity.getResources().getString(title), activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null, view);
    }

    public void showAlertDialog(int title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, View view) {
        showAlertDialog(activity.getResources().getString(title), message, cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null, view);
    }

    public void showAlertDialog(String title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton,View view) {
        showAlertDialog(title, message, cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null, view);
    }

    public void showAlertDialog(String title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton,View view) {
        showAlertDialog(title, activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null, view);
    }

    public void showAlertDialog(int title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton, View view) {
        showAlertDialog(activity.getResources().getString(title), activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, textNegativeButton, onClickListenerNegativeButton, view);
    }

    public void showAlertDialog(int title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton, View view) {
        showAlertDialog(activity.getResources().getString(title), message, cancelable, textPositiveButton, onClickListenerPositiveButton, textNegativeButton, onClickListenerNegativeButton, view);
    }

    public void showAlertDialog(String title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton, View view) {
        showAlertDialog(title, activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, textNegativeButton, onClickListenerNegativeButton, view);
    }

    public void showAlertDialog(String title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        if (view != null) {
            builder.setView(view);
        }
        if (onClickListenerPositiveButton != null) {
            builder.setPositiveButton(textPositiveButton, onClickListenerPositiveButton);
        }
        if (onClickListenerNegativeButton != null) {
            builder.setNegativeButton(textNegativeButton, onClickListenerNegativeButton);
        }
        builder.show();
    }


}
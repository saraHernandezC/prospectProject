package com.example.sarah.prospectsproject.controllers.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.controllers.views.IBaseView;
import com.example.sarah.prospectsproject.helper.CustomAlertDialog;
import com.example.sarah.prospectsproject.helper.CustomSharedPreferences;
import com.example.sarah.prospectsproject.helper.DataBaseHelper;
import com.example.sarah.prospectsproject.helper.IValidateInternet;
import com.example.sarah.prospectsproject.helper.SetContentEditProspects;
import com.example.sarah.prospectsproject.helper.ValidateInternet;
import com.example.sarah.prospectsproject.presenters.BasePresenter;

/**
 * Created by sarah on 2/09/2018.
 */


@SuppressLint("Registered")
public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private T presenter;
    private IValidateInternet validateInternet;
    private ProgressDialog progressDialog;
    private CustomSharedPreferences customSharedPreferences;
    private CustomAlertDialog customAlertDialog;
    private SetContentEditProspects contentEditProspects;
    private Login user;
    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.validateInternet = new ValidateInternet(this);
        this.customSharedPreferences = new CustomSharedPreferences(this);
        this.customAlertDialog = new CustomAlertDialog(this);
        this.contentEditProspects = new SetContentEditProspects(this);
        this.dataBaseHelper = new DataBaseHelper(this);
    }

    @Override
    public void showProgressDialog(final int text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(getResources().getString(text));
                progressDialog.show();
            }
        });

    }



    @Override
    public void dismissProgressDialog() {
        this.progressDialog.dismiss();
    }


    @Override
    public void finishActivity() {
        this.finish();
    }


    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final int title, final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }


    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final int title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }

    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final String title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }


    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final String title, final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }

    @Override
    public void showAlertDialogUnauthorized(final int title, final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralUnauthoried(title, message);
            }
        });
    }


    private DialogInterface.OnClickListener getPositiveButtonOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };
    }



    private void showAlertDialogGeneralUnauthoried(int title, int message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getPositiveButtonOnClickListener(), null);

    }
    private void showAlertDialogGeneralInformation(int title, int message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener(), null);
    }

    private void showAlertDialogGeneralInformation(int title, String message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener(), null);
    }

    private void showAlertDialogGeneralInformation(String title, String message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener(), null);
    }

    private void showAlertDialogGeneralInformation(String title, int message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener(), null);
    }



    public DialogInterface.OnClickListener getDefaulPositiveButtonOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }


    public void createProgressDialog() {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setCancelable(false);
    }


    protected T getPresenter() {
        return presenter;
    }

    protected void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public IValidateInternet getValidateInternet() {
        if (validateInternet == null) {
            validateInternet = new ValidateInternet(this);
        }
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }

    public CustomAlertDialog getCustomAlertDialog() {
        return customAlertDialog;
    }

    public void setCustomAlertDialog(CustomAlertDialog customAlertDialog) {
        this.customAlertDialog = customAlertDialog;
    }

    public SetContentEditProspects getContentEditProspects() {
        return contentEditProspects;
    }

    public void setContentEditProspects(SetContentEditProspects contentEditProspects) {
        this.contentEditProspects = contentEditProspects;
    }

    public CustomSharedPreferences getCustomSharedPreferences() {
        if (customSharedPreferences == null) {
            customSharedPreferences = new CustomSharedPreferences(this);
        }
        return customSharedPreferences;
    }

    public DataBaseHelper getDataBaseHelper(){
        if (dataBaseHelper == null){
            dataBaseHelper = new DataBaseHelper(this);
        }
        return dataBaseHelper;
    }

    public void setCustomSharedPreferences(CustomSharedPreferences customSharedPreferences) {
        this.customSharedPreferences = customSharedPreferences;
    }


}
package com.example.sarah.prospectsproject.controllers.views;

/**
 * Created by sarah on 2/09/2018.
 */

public interface IBaseView {

    void showProgressDialog(int text);

    void dismissProgressDialog();

    void finishActivity();

    void showAlertDialogGeneralInformationOnUiThread(int title, int message);

    void showAlertDialogGeneralInformationOnUiThread(int title, String message);

    void showAlertDialogGeneralInformationOnUiThread(String title, String message);

    void showAlertDialogGeneralInformationOnUiThread(String title, int message);

    void showAlertDialogUnauthorized(int title, int message);




}

package com.example.sarah.prospectsproject.presenters.login;


import android.widget.CheckBox;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessLogic.login.ILoginBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.controllers.views.intefaces_login.ILoginView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.presenters.BasePresenter;

/**
 * Created by sarah on 1/09/2018.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private final ILoginBusinessLogic loginBusinessLogic;

    public LoginPresenter(ILoginBusinessLogic loginBusinessLogic) {
        this.loginBusinessLogic = loginBusinessLogic;
    }

    public void validateInternetToGetToken(String email, String password) {
        if (getValidateInternet().isConnected()) {
            createThreadToGetToken(email,password);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetToken(final String email, final String password) {
        getView().showProgressDialog(R.string.text_please_wait);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                login(email, password);
            }
        });
        thread.start();

    }

    public void login( String email, String password) {
        try {
            Login getLogin = loginBusinessLogic.getLogin(email, password);
            if (getLogin.isSuccess()) {
                getView().correctLogin(getLogin.getToken());
            } else {
                getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
            }
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, repositoryError.getMensaje());
        }finally {
            getView().dismissProgressDialog();
        }
    }


}

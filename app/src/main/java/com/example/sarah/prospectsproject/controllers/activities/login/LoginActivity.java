package com.example.sarah.prospectsproject.controllers.activities.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.controllers.activities.BaseActivity;
import com.example.sarah.prospectsproject.controllers.activities.prospects.ProspectsListDataBase;
import com.example.sarah.prospectsproject.controllers.views.intefaces_login.ILoginView;
import com.example.sarah.prospectsproject.dependencyInjection.DomainModule;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.EmailValidation;
import com.example.sarah.prospectsproject.presenters.login.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    CheckBox checkBoxKeepAlive;

    String emailText;
    String passwordText;

    EmailValidation emailFormatValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createProgressDialog();
        setPresenter(new LoginPresenter(DomainModule.getLoginBussinesLogicInstance(getCustomSharedPreferences())));
        getPresenter().inject(this, getValidateInternet());
        emailFormatValidation = new EmailValidation();

        loadViews();
        loadListenersToTheControls();


    }


    private void loadListenersToTheControls() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFieldsInformation();
                validateCheckBoxKeepAlive();
                validateFields();
            }
        });
    }

    private void validateFields() {
        if (validateEmptyStrings()) {
            showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.empty_fields);
        } else {
            validateEmailFormat(emailText);
        }
    }

    private void validateEmailFormat(String email) {
        if (emailFormatValidation.validateEmailFormat(email)) {
            getPresenter().validateInternetToGetToken(emailText, passwordText);
        } else {
            showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.wrong_email_format);
        }
    }

    private boolean validateEmptyStrings() {
        return emailText.isEmpty() || passwordText.isEmpty();
    }

    private void getFieldsInformation() {
        emailText = editTextEmail.getText().toString().trim();
        passwordText = editTextPassword.getText().toString().trim();
    }

    private void loadViews() {
        editTextEmail = findViewById(R.id.editText_email);
        editTextPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_continue);
        checkBoxKeepAlive = findViewById(R.id.checkBox_remember);
    }

    private void validateCheckBoxKeepAlive() {
        getCustomSharedPreferences().addBoolean(Constants.ESTATE_CHECK_KEY, checkBoxKeepAlive.isChecked());
    }

    @Override
    public void correctLogin(String token) {
        intentToProspectListDataBase(token);
    }

    private void intentToProspectListDataBase(String token){
        getCustomSharedPreferences().addString(Constants.TOKEN,token);
        Intent intent = new Intent(this, ProspectsListDataBase.class);
        startActivityForResult(intent, Constants.DEFAUL_REQUEST_CODE);
        finish();
    }
}

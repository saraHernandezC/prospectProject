package com.example.sarah.prospectsproject.controllers.activities.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.controllers.activities.BaseActivity;
import com.example.sarah.prospectsproject.controllers.activities.login.LoginActivity;
import com.example.sarah.prospectsproject.controllers.activities.prospects.ProspectsListDataBase;
import com.example.sarah.prospectsproject.controllers.views.interfaces_splash.ISplashView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.presenters.splash.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setPresenter(new SplashPresenter());
        getPresenter().inject(this, getValidateInternet());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getMainScreen(getCustomSharedPreferences().getBoolean(Constants.ESTATE_CHECK_KEY));
            }
        }, Constants.SPLASH_SCREEN_TIME);
    }

    private void getMainScreen(boolean stateCheckKeepAlive){
        getPresenter().validateStateCheckKeepAlive(stateCheckKeepAlive);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.DEFAUL_REQUEST_CODE);
        finish();
    }

    @Override
    public void startProspectsListDatabase() {
        Intent intent = new Intent(this, ProspectsListDataBase.class);
        startActivityForResult(intent, Constants.DEFAUL_REQUEST_CODE);
        finish();
    }
}

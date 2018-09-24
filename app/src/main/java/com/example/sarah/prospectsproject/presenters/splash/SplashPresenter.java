package com.example.sarah.prospectsproject.presenters.splash;

import com.example.sarah.prospectsproject.controllers.views.interfaces_splash.ISplashView;
import com.example.sarah.prospectsproject.presenters.BasePresenter;

/**
 * Created by sarah on 11/09/2018.
 */

public class SplashPresenter extends BasePresenter<ISplashView> {
    public void validateStateCheckKeepAlive(boolean stateCheckKeepAlive) {
        if (stateCheckKeepAlive){
            getView().startProspectsListDatabase();
        }
        else {
            getView().startLoginActivity();
        }
    }
}

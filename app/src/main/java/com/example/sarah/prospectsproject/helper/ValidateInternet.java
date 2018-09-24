package com.example.sarah.prospectsproject.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sarah on 2/09/2018.
 */

public class ValidateInternet implements IValidateInternet {

    private final Context context;

    public ValidateInternet(Context context) {
        this.context = context;
    }

    @Override
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}

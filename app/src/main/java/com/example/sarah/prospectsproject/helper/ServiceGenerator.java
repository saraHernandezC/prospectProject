package com.example.sarah.prospectsproject.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sarah on 11/09/2018.
 */

public class ServiceGenerator {
    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    protected static Retrofit retrofit = builder.build();
}

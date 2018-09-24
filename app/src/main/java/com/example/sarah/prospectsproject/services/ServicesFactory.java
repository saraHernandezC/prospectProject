package com.example.sarah.prospectsproject.services;

import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.ICustomSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sarah on 10/09/2018.
 */

public class ServicesFactory {

    private static final String API_BASE_PATH = Constants.URL_PROSPECTS;
    private final Retrofit restAdapter;



    public ServicesFactory(final ICustomSharedPreferences customSharedPreferences) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constants.ONE_HUNDRED_AND_TWENTY, TimeUnit.SECONDS)
                .writeTimeout(Constants.ONE_HUNDRED_AND_TWENTY, TimeUnit.SECONDS)
                .readTimeout(Constants.ONE_HUNDRED_AND_TWENTY, TimeUnit.SECONDS)
                .addInterceptor( new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = customSharedPreferences.getString(Constants.TOKEN);
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().addHeader("Accept", "application/json");
                if (token != null) {
                    builder.addHeader(Constants.TOKEN_SERVICES, token);
                }
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();
        restAdapter = new Retrofit.Builder()
                .baseUrl(API_BASE_PATH)
                .client(okHttpClient)
                .addConverterFactory(getGsonConverter())
                .build();
    }

    private Interceptor getRequestInterceptor(final ICustomSharedPreferences customSharedPreferences) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = customSharedPreferences.getString(Constants.TOKEN);
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().addHeader("Accept", "application/json");
                if (token != null) {
                    builder.addHeader(Constants.TOKEN, token);
                }
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }
    private Converter.Factory getGsonConverter() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return GsonConverterFactory.create(gson);
    }





    /**
     * Obtiene la instancia del servicio.
     *
     * @param service Tipo de servicio.
     * @return Instancia del servicio.
     */
    public Object getInstance(Class service) {
        return restAdapter.create(service);
    }
}

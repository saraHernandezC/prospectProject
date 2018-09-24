package com.example.sarah.prospectsproject.services;

import com.example.sarah.prospectsproject.dto.LoginDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sarah on 1/09/2018.
 */

public interface IServicesLogin {

    @GET("application/login")
    Call<LoginDTO> getLogin(@Query("email") String email, @Query("password") String password);
}

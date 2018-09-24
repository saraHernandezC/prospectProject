package com.example.sarah.prospectsproject.services;

import com.example.sarah.prospectsproject.dto.LoginDTO;
import com.example.sarah.prospectsproject.dto.ProspectDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sarah on 11/09/2018.
 */

public interface IServicesProspectsList {

    @GET("sch/prospects.json")
    Call<List<ProspectDTO>> getProspectsList();
}

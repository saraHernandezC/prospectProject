package com.example.sarah.prospectsproject.repositories.loginRepository;

import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.dto.LoginDTO;
import com.example.sarah.prospectsproject.helper.ICustomSharedPreferences;
import com.example.sarah.prospectsproject.helper.Mapper;
import com.example.sarah.prospectsproject.services.IServicesLogin;
import com.example.sarah.prospectsproject.services.ServicesFactory;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sarah on 2/09/2018.
 */

public class LoginRepository implements ILoginRepository {
    private final IServicesLogin services;

    public LoginRepository(ICustomSharedPreferences customSharedPreferences) {
        ServicesFactory servicesFactory = new ServicesFactory(customSharedPreferences);
        services = (IServicesLogin) servicesFactory.getInstance(IServicesLogin.class);
    }

    @Override
    public Login getLogin(String email, String password) throws RepositoryError {
        try {
            Call<LoginDTO> call = services.getLogin(email, password);
            Response<LoginDTO> loginDTO = call.execute();
            if (loginDTO.errorBody() != null) {
                throw Mapper.validateTheBodyToGetRepositoryError(loginDTO.code(), loginDTO.errorBody());
            } else {
                return Mapper.convertLoginDTOToDomain(loginDTO.body());
            }
        } catch (IOException exception) {
            throw Mapper.validateTimeOutToGetRepositoryError(exception);
        }
    }
}

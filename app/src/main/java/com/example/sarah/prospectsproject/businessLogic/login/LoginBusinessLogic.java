package com.example.sarah.prospectsproject.businessLogic.login;

import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.repositories.loginRepository.ILoginRepository;

/**
 * Created by sarah on 1/09/2018.
 */

public class LoginBusinessLogic implements ILoginBusinessLogic {

    private final ILoginRepository loginRepository;

    public LoginBusinessLogic(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login getLogin(String email, String password) throws RepositoryError {
       return loginRepository.getLogin(email,password);
    }
}

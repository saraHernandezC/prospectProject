package com.example.sarah.prospectsproject.businessLogic.login;

import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.businessModel.Login;

/**
 * Created by sarah on 1/09/2018.
 */

public interface ILoginBusinessLogic {
    Login getLogin(String email, String password) throws RepositoryError;
}

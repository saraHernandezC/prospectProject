package com.example.sarah.prospectsproject.repositories.loginRepository;

import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.businessModel.Login;

/**
 * Created by sarah on 2/09/2018.
 */

public interface ILoginRepository {

    Login getLogin(String email, String password)  throws RepositoryError;
}

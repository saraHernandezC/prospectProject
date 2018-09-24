package com.example.sarah.prospectsproject.repositories.loginRepository;

import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;

/**
 * Created by sarah on 10/09/2018.
 */

public class LoginRepositoryTest implements ILoginRepository {
    @Override
    public Login getLogin(String email, String password) throws RepositoryError {
        Login login = new Login();

        return login;
    }

}

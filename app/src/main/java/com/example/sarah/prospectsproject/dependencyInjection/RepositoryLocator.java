package com.example.sarah.prospectsproject.dependencyInjection;

import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.ICustomSharedPreferences;
import com.example.sarah.prospectsproject.repositories.loginRepository.ILoginRepository;
import com.example.sarah.prospectsproject.repositories.loginRepository.LoginRepository;
import com.example.sarah.prospectsproject.repositories.loginRepository.LoginRepositoryTest;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.IProspectsListRepository;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.ProspectsListRepository;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.ProspectsListRepositoryTest;

/**
 * Created by sarah on 10/09/2018.
 */

public class RepositoryLocator {
    static ILoginRepository getLoginRepositoryInstance(ICustomSharedPreferences customSharedPreferences){
        if (Constants.IS_DEBUG_LOGIN) {
            return new LoginRepositoryTest() {
            };
        } else {
            return new LoginRepository(customSharedPreferences);
        }
    }

    static IProspectsListRepository getProspectsListRepositoryInstance(ICustomSharedPreferences customSharedPreferences){
        if (Constants.IS_DEBUG_LOGIN) {
            return new ProspectsListRepositoryTest() {
            };
        } else {
            return new ProspectsListRepository(customSharedPreferences);
        }
    }

}

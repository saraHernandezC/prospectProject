package com.example.sarah.prospectsproject.dependencyInjection;

import com.example.sarah.prospectsproject.businessLogic.login.LoginBusinessLogic;
import com.example.sarah.prospectsproject.businessLogic.prospectsList.ProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.helper.ICustomSharedPreferences;

/**
 * Created by sarah on 10/09/2018.
 */

public class DomainModule {
    public static LoginBusinessLogic getLoginBussinesLogicInstance(ICustomSharedPreferences customSharedPreferences){
        return new LoginBusinessLogic(RepositoryLocator.getLoginRepositoryInstance(customSharedPreferences));
    }

    public static ProspectsListBusinessLogic getProspectsBussinesLogicInstance(ICustomSharedPreferences customSharedPreferences){
        return new ProspectsListBusinessLogic(RepositoryLocator.getProspectsListRepositoryInstance(customSharedPreferences));
    }


}

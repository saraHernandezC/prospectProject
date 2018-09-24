package com.example.sarah.prospectsproject.presenters.prospectList;


import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessLogic.prospectsList.IProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListDataBaseView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.presenters.BasePresenter;

import java.util.List;

/**
 * Created by sarah on 11/09/2018.
 */

public class ProspectsListDataBasePresenter extends BasePresenter<IProspectListDataBaseView> {

    private final IProspectsListBusinessLogic prospectBusinessLogic;

    public ProspectsListDataBasePresenter(IProspectsListBusinessLogic prospectsListBusinessLogic) {
        this.prospectBusinessLogic = prospectsListBusinessLogic;
    }


    public void validateInternetToGetProspectsList() {
        if (getValidateInternet().isConnected()) {
            createThreadToGetProspectsList();
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetProspectsList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProspectsList();
            }
        });
        thread.start();
    }

    public void getProspectsList() {
        try {
            List<Prospect> getProspect = prospectBusinessLogic.getProspectsList();
            if (getProspect.size() > Constants.ZERO) {
                getView().correctGetProspectsList(getProspect);
            } else {
                getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
            }
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
        }
    }
}

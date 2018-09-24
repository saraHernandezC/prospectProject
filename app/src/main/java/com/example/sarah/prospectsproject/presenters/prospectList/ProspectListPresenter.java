package com.example.sarah.prospectsproject.presenters.prospectList;

import android.content.Context;

import com.example.sarah.prospectsproject.businessLogic.prospectsList.IProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.presenters.BasePresenter;

import java.util.List;

/**
 * Created by sarah on 12/09/2018.
 */

public class ProspectListPresenter extends BasePresenter<IProspectListView> {

    private final IProspectsListBusinessLogic prospectsListBusinessLogic;


    public ProspectListPresenter(IProspectsListBusinessLogic prospectsListBusinessLogic) {
        this.prospectsListBusinessLogic = prospectsListBusinessLogic;
    }

    public void getProspectsFromDataBase(Context context) {

        try {
            List<Prospect>prospects =prospectsListBusinessLogic.getProspectsListFromDataBase(context);
                getView().correctGetProspectsListDataBase(prospects);


        } catch (RepositoryError repositoryError){
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
        }
    }

    public void createThreadToGetProspectsListFromDataBase(final Context context) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProspectsFromDataBase(context);
            }
        });
        thread.start();
    }

    public void createThreadToDropProspect(final Prospect prospect, final Context context) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dropProspectsFromDataBase(prospect,context);
            }
        });
        thread.start();
    }

    private void dropProspectsFromDataBase(Prospect prospect, Context context) {
            try {
                prospectsListBusinessLogic.dropProspectFromDataBase(context, prospect);
                getView().correctDropProspectFromDataBase(prospect);
            } catch (RepositoryError repositoryError){
                getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
            }

    }

    public void createThreadToEditProspect(final Prospect prospect, final Context context,final String id, final String name, final String lastName, final String identification, final String telephone, final int state) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                editProspectsFromDataBase(prospect,context,id,name,lastName,identification,telephone,state);
            }
        });
        thread.start();
    }

    private void editProspectsFromDataBase(Prospect prospect, Context context, String id,String name, String lastName, String identification, String telephone, int state){
        try {
            if (prospectsListBusinessLogic.editProspectFromDataBase(prospect, context,id,name,lastName,identification,telephone,state))
            {
                getView().correctEditProspectFromDataBase();
            }
           else {
                getView().failedEditProspectFromDataBase();
            }

        } catch (RepositoryError repositoryError){
            getView().showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
        }
    }

    public void alertFillAllFields() {
        getView().alertFillAllFields();
    }
}

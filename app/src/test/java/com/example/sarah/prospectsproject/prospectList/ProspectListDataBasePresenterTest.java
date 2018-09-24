package com.example.sarah.prospectsproject.prospectList;

import android.content.Context;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessLogic.prospectsList.IProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessLogic.prospectsList.ProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListDataBaseView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.IValidateInternet;
import com.example.sarah.prospectsproject.presenters.prospectList.ProspectListPresenter;
import com.example.sarah.prospectsproject.presenters.prospectList.ProspectsListDataBasePresenter;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.IProspectsListRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sarah on 14/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)

public class ProspectListDataBasePresenterTest {
    ArrayList<Prospect>prospects = new ArrayList<>();
    private ProspectsListDataBasePresenter prospectsListDataBasePresenter;
    @Mock
    private
    IProspectsListRepository iProspectsListRepository;

    @Mock
    private
    IProspectsListBusinessLogic iProspectsListBusinessLogic;

    @Mock
    private
    IProspectListDataBaseView iProspectListDataBaseView;

    @Mock
    private
    IValidateInternet validateInternet;


    private ArrayList<Prospect>  getProspects(){

        Prospect prospect = new Prospect();
        prospect.setId("1");
        prospect.setName("sara");
        prospect.setSurname("hernández");
        prospect.setSchProspectIdentification("123");
        prospect.setTelephone("123");
        prospect.setStatusCd(1);
        prospects.add(prospect);
        return prospects;
    }

    @Before
    public void setUp() {
        iProspectsListBusinessLogic = Mockito.spy(new ProspectsListBusinessLogic(iProspectsListRepository));
        prospectsListDataBasePresenter = Mockito.spy(new ProspectsListDataBasePresenter(iProspectsListBusinessLogic));
        prospectsListDataBasePresenter.inject(iProspectListDataBaseView, validateInternet);
    }

    @Test
    public void methodValidateInternetToGetProspectsWithOutConnectionShouldShowAlertDialog() {
        when(validateInternet.isConnected()).thenReturn(false);
        prospectsListDataBasePresenter.validateInternetToGetProspectsList();
        verify(iProspectListDataBaseView).showAlertDialogGeneralInformationOnUiThread(Constants.user, R.string.text_validate_internet);
    }

    @Test
    public void methodValidateInternetToGetProspectsWithConnectionShouldCallMethodCreateThreadToGetProspects() {
        when(validateInternet.isConnected()).thenReturn(true);
        prospectsListDataBasePresenter.validateInternetToGetProspectsList();
        verify(prospectsListDataBasePresenter).createThreadToGetProspectsList();
    }

    @Test
    public void methodGetProspectsCallMehtodGetProspectsInProspectsBusinessLogic() throws RepositoryError {
        when(iProspectsListRepository.getProspectList()).thenReturn(getProspects());
        prospectsListDataBasePresenter.getProspectsList();
        verify(iProspectListDataBaseView).correctGetProspectsList(getProspects());
    }
    @Test
    public void methoGetProspectsShouldShowAnAlertDialogWhenReturnAnException() throws RepositoryError {
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        repositoryError.setMensaje("Error");
        when(iProspectsListRepository.getProspectList()).thenThrow(repositoryError);
        prospectsListDataBasePresenter.getProspectsList();
        verify(iProspectListDataBaseView).showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
    }
}

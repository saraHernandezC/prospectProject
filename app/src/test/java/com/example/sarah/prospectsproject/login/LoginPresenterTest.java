package com.example.sarah.prospectsproject.login;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessLogic.login.ILoginBusinessLogic;
import com.example.sarah.prospectsproject.businessLogic.login.LoginBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.controllers.views.intefaces_login.ILoginView;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.helper.IValidateInternet;
import com.example.sarah.prospectsproject.presenters.login.LoginPresenter;
import com.example.sarah.prospectsproject.repositories.loginRepository.ILoginRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sarah on 14/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    private LoginPresenter loginPresenter;

    @Mock
    private
    ILoginRepository loginRepository;

    @Mock
    private
    ILoginBusinessLogic iLoginBusinessLogic;

    @Mock
    private
    ILoginView iLoginView;

    @Mock
    private
    IValidateInternet validateInternet;


    private final String email = "directo@directo.com";
    private final String password = "directo123";
    private final String token = "12345";



    private Login getLogin(){
        Login login = new Login();
        login.setSuccess(true);
        login.setToken("12345");
        return login;
    }
    @Before
    public void setUp() {
        iLoginBusinessLogic = Mockito.spy(new LoginBusinessLogic(loginRepository));
        loginPresenter = Mockito.spy(new LoginPresenter(iLoginBusinessLogic));
        loginPresenter.inject(iLoginView, validateInternet);
    }

    @Test
    public void methodValidateInternetToGetLoginWithOutConnectionShouldShowAlertDialog() {
        when(validateInternet.isConnected()).thenReturn(false);
        loginPresenter.validateInternetToGetToken(email,password);
        verify(iLoginView).showAlertDialogGeneralInformationOnUiThread(Constants.user, R.string.text_validate_internet);
    }

    @Test
    public void methodValidateInternetToGetLoginWithConnectionShouldCallMethodCreateThreadToGetLogin() {
        when(validateInternet.isConnected()).thenReturn(true);
        loginPresenter.validateInternetToGetToken(email,password);
        verify(loginPresenter).createThreadToGetToken(email,password);
    }

    @Test
    public void methodCreateThreadToGetLoginShouldShowProgressDialog() {
        loginPresenter.createThreadToGetToken(email,password);
        verify(iLoginView).showProgressDialog(R.string.text_please_wait);
    }

    @Test
    public void methodGetLoginCallMehtodGetLoginInLoginBusinessLogic() throws RepositoryError {
        Login login = getLogin();
        when(loginRepository.getLogin(email,password)).thenReturn(login);
        loginPresenter.login(email,password);
        verify(iLoginView).correctLogin(token);
        verify(iLoginView).dismissProgressDialog();
    }

    @Test
    public void methodGetLoginShouldShowAnAlertDialogWhenReturnAnException() throws RepositoryError {
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        repositoryError.setMensaje(Constants.DEFAUL_ERROR);
        when(loginRepository.getLogin(email,password)).thenThrow(repositoryError);
        loginPresenter.login(email,password);
        verify(iLoginView).showAlertDialogGeneralInformationOnUiThread(Constants.user, Constants.DEFAUL_ERROR);
        verify(iLoginView).dismissProgressDialog();
    }
}

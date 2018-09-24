package com.example.sarah.prospectsproject.login;

import com.example.sarah.prospectsproject.businessLogic.login.ILoginBusinessLogic;
import com.example.sarah.prospectsproject.businessLogic.login.LoginBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.repositories.loginRepository.ILoginRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sarah on 14/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)

public class LoginBusinessLogicTest {
    @Mock
    private
    ILoginBusinessLogic iLoginBusinessLogic;

    @Mock
    private
    ILoginRepository iLoginRepository;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        iLoginBusinessLogic = new LoginBusinessLogic(iLoginRepository);
    }
    private final String email = "directo@directo.com";
    private final String password = "directo123";
    private final String token = "12345";



    @Test
    public void methodWithCorrectParametersShouldCallMethodGetLoginInRepositoryTest() throws RepositoryError {
        iLoginBusinessLogic.getLogin(email,password);
        verify(iLoginRepository).getLogin(email,password);
    }

    @Test
    public void methodLoginShouldReturnEntityLoginFromRepositoryTest() throws RepositoryError {
        Login login = new Login();
        when(iLoginRepository.getLogin(email,password)).thenReturn(login);
        Login loginTest = iLoginBusinessLogic.getLogin(email,password);
        Assert.assertEquals(login, loginTest);
    }
}

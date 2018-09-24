package com.example.sarah.prospectsproject.helper;

import android.database.Cursor;

import com.example.sarah.prospectsproject.businessModel.Login;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.dto.LoginDTO;
import com.example.sarah.prospectsproject.dto.ProspectDTO;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by sarah on 1/09/2018.
 */

public class Mapper {
    public static RepositoryError validateTheBodyToGetRepositoryError(int code, ResponseBody messageError) {
        if (code == Constants.UNAUTHORIZED_ERROR_CODE || code == Constants.ERROR_SERVER) {
            return defaultRepository(code);
        } else if (messageError != null) {
            Converter<ResponseBody, RepositoryError> converter =
                    ServiceGenerator.retrofit.responseBodyConverter(RepositoryError.class, new Annotation[0]);
            RepositoryError error;
            try {
                error = converter.convert(messageError);
            } catch (IOException e) {
                return defaultRepository(code);
            }
            return error;
        } else {
            return defaultRepository(code);
        }
    }
    public static RepositoryError validateTimeOutToGetRepositoryError(IOException e) {
        Exception exception = new Exception(e);
        if (exception.getCause() != null && exception.getCause() instanceof SocketTimeoutException
                || exception.getCause() instanceof InterruptedIOException) {
            RepositoryError repositoryError = new RepositoryError(Constants.REQUEST_TIMEOUT_ERROR_MESSAGE);
            repositoryError.setIdError(Constants.DEFAUL_ERROR_CODE);
            return repositoryError;
        }
        return validateTheBodyToGetRepositoryError(Constants.ZERO, null);
    }
    private static RepositoryError defaultRepository(int code) {
        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);
        repositoryError.setIdError(code);
        repositoryError.setMensaje(Constants.DEFAUL_ERROR);
        return repositoryError;
    }


    public static Login convertLoginDTOToDomain(LoginDTO loginDTO) {
        Login login = new Login();
        login.setSuccess(loginDTO.isSuccess());
        login.setToken(loginDTO.getAuthToken());
        return login;
    }

    public static List<Prospect> convertProspectsListDTOToDomain(List<ProspectDTO>prospects) {
        List<Prospect> prospectList = new ArrayList<>();

        for (ProspectDTO prospect : prospects){
            Prospect prospecto = new Prospect();

            prospecto.setId(prospect.getId());
            prospecto.setName(prospect.getName());
            prospecto.setSurname(prospect.getSurname());
            prospecto.setSchProspectIdentification(prospect.getSchProspectIdentification());
            prospecto.setStatusCd(prospect.getStatusCd());
            prospecto.setTelephone(prospect.getTelephone());

            prospectList.add(prospecto);

        }
        return prospectList;
    }

    public static List<Prospect> convertProspectsSQLToList(Cursor prospectList){
        ArrayList<Prospect> prospectQLList = new ArrayList<>();

        while (prospectList.moveToNext()){
            Prospect prospect = new Prospect();

            prospect.setId(prospectList.getString(Constants.ZERO));
            prospect.setName(prospectList.getString(Constants.ONE));
            prospect.setSurname(prospectList.getString(Constants.TWO));
            prospect.setSchProspectIdentification(prospectList.getString(Constants.THREE));
            prospect.setTelephone(prospectList.getString(Constants.FOUR));
            prospect.setStatusCd(prospectList.getInt(Constants.FIVE));
            prospectQLList.add(prospect);
        }
        return prospectQLList;
    }

}

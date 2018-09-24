package com.example.sarah.prospectsproject.repositories.prospectsListRespository;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.dto.ProspectDTO;
import com.example.sarah.prospectsproject.helper.ICustomSharedPreferences;
import com.example.sarah.prospectsproject.helper.Mapper;
import com.example.sarah.prospectsproject.services.IServicesProspectsList;
import com.example.sarah.prospectsproject.services.ServicesFactory;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sarah on 11/09/2018.
 */

public class ProspectsListRepository implements IProspectsListRepository {

    private final IServicesProspectsList services;

    public ProspectsListRepository(ICustomSharedPreferences customSharedPreferences) {
        ServicesFactory servicesFactory = new ServicesFactory(customSharedPreferences);
        services = (IServicesProspectsList) servicesFactory.getInstance(IServicesProspectsList.class);
    }

    @Override
    public List<Prospect> getProspectList()throws RepositoryError {
        try {
            Call<List<ProspectDTO>> call = services.getProspectsList();
            Response<List<ProspectDTO>> prospectDTOResponse = call.execute();

            if (prospectDTOResponse.errorBody() != null) {
                throw Mapper.validateTheBodyToGetRepositoryError(prospectDTOResponse.code(), prospectDTOResponse.errorBody());
            } else {
                return Mapper.convertProspectsListDTOToDomain(prospectDTOResponse.body());
            }
        } catch (IOException exception) {
            throw Mapper.validateTimeOutToGetRepositoryError(exception);
        }
    }
}

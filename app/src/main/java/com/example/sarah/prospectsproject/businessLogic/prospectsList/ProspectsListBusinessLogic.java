package com.example.sarah.prospectsproject.businessLogic.prospectsList;

import android.content.Context;
import android.database.Cursor;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.helper.DataBaseHelper;
import com.example.sarah.prospectsproject.helper.Mapper;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.IProspectsListRepository;

import java.util.List;

/**
 * Created by sarah on 11/09/2018.
 */

public class ProspectsListBusinessLogic implements IProspectsListBusinessLogic {

    private final IProspectsListRepository prospectsListRepository;

    public ProspectsListBusinessLogic(IProspectsListRepository prospectsListRepository) {
        this.prospectsListRepository = prospectsListRepository;
    }

    @Override
    public List<Prospect> getProspectsList()throws RepositoryError {
        return prospectsListRepository.getProspectList();
    }

    @Override
    public List<Prospect> getProspectsListFromDataBase(Context context) {
        DataBaseHelper sqlHelper = new DataBaseHelper(context);
        Cursor prospectList = sqlHelper.readAllData();
        return  Mapper.convertProspectsSQLToList(prospectList);
    }

    @Override
    public void dropProspectFromDataBase(Context context, Prospect prospect) {
        DataBaseHelper sqlHelper = new DataBaseHelper(context);
        sqlHelper.deleteData(prospect.getId());

    }

    @Override
    public boolean editProspectFromDataBase(Prospect prospect, Context context, String id, String name, String lastName, String identification, String telephone, int state) throws RepositoryError {
        DataBaseHelper sqlHelper = new DataBaseHelper(context);

      return sqlHelper.updateData(id,
                            name,
                            lastName,
                            telephone,
                            identification,
                            state);
    }

}

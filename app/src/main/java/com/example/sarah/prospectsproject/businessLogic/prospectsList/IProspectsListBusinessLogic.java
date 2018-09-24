package com.example.sarah.prospectsproject.businessLogic.prospectsList;

import android.content.Context;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;

import java.util.List;

/**
 * Created by sarah on 11/09/2018.
 */

public interface IProspectsListBusinessLogic {
    List<Prospect> getProspectsList()throws RepositoryError;

    List<Prospect> getProspectsListFromDataBase(Context context)throws RepositoryError;

    void dropProspectFromDataBase(Context context, Prospect prospect)throws RepositoryError;

    boolean editProspectFromDataBase(Prospect prospect, Context context,String id, String name, String lastName, String identification, String telephone, int state) throws RepositoryError;
}

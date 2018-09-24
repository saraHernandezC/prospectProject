package com.example.sarah.prospectsproject.repositories.prospectsListRespository;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;

import java.util.List;

/**
 * Created by sarah on 11/09/2018.
 */

public interface IProspectsListRepository {
    List<Prospect> getProspectList()throws RepositoryError;
}

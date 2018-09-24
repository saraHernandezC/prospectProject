package com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.controllers.views.IBaseView;

import java.util.List;

/**
 * Created by sarah on 11/09/2018.
 */

public interface IProspectListDataBaseView extends IBaseView {
    void setProspectsListDataBase();

    void correctGetProspectsList(List<Prospect> getProspect);
}

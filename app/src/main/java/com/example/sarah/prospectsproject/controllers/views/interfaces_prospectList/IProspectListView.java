package com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList;

import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.controllers.views.IBaseView;

import java.util.List;

/**
 * Created by sarah on 12/09/2018.
 */

public interface IProspectListView extends IBaseView {
    void correctGetProspectsListDataBase(List<Prospect> prospects);

    void longClick(Prospect prospect);
    void inflateProspectInformationLayout(Prospect prospect);
    void correctDropProspectFromDataBase(Prospect prospect);

    void correctEditProspectFromDataBase();

    void failedEditProspectFromDataBase();

    void alertFillAllFields();
}

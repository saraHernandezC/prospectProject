package com.example.sarah.prospectsproject.prospectList;

import android.content.Context;

import com.example.sarah.prospectsproject.businessLogic.prospectsList.IProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessLogic.prospectsList.ProspectsListBusinessLogic;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.businessModel.RepositoryError;
import com.example.sarah.prospectsproject.repositories.prospectsListRespository.IProspectsListRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sarah on 14/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProspectListDataBaseBusinessLogicTest {
    ArrayList<Prospect>prospects = new ArrayList<>();
    @Mock
    private
    IProspectsListBusinessLogic iProspectsListBusinessLogic;

    @Mock
    private
    IProspectsListRepository iProspectsListRepository;

    @Mock
    private Context context;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        iProspectsListBusinessLogic = new ProspectsListBusinessLogic(iProspectsListRepository);
    }

    private Prospect getProspect(){
        Prospect prospect = new Prospect();
        prospect.setId("1");
        prospect.setName("sara");
        prospect.setSurname("hernández");
        prospect.setSchProspectIdentification("123");
        prospect.setTelephone("123");
        prospect.setStatusCd(1);
        return prospect;
    }

    private ArrayList<Prospect> getProspects(){
        prospects.add(getProspect());
        return prospects;
    }

    @Test
    public void methodGetProspectListShouldCallMethodGetProspectsInRepository() throws RepositoryError {
        iProspectsListBusinessLogic.getProspectsList();
        verify(iProspectsListRepository).getProspectList();
    }

    @Test
    public void methodLoginShouldReturnListEntityProspectFromRepository() throws RepositoryError {
        when(iProspectsListRepository.getProspectList()).thenReturn(prospects);
        List<Prospect> loginTest = iProspectsListBusinessLogic.getProspectsList();
        Assert.assertEquals(getProspects(), loginTest);
    }


}

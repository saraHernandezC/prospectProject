package com.example.sarah.prospectsproject.controllers.activities.prospects;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.controllers.activities.BaseActivity;
import com.example.sarah.prospectsproject.controllers.activities.login.LoginActivity;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListDataBaseView;
import com.example.sarah.prospectsproject.dependencyInjection.DomainModule;
import com.example.sarah.prospectsproject.helper.Constants;
import com.example.sarah.prospectsproject.presenters.prospectList.ProspectsListDataBasePresenter;

import java.util.List;

public class ProspectsListDataBase extends BaseActivity<ProspectsListDataBasePresenter>
        implements NavigationView.OnNavigationItemSelectedListener,IProspectListDataBaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospects_list_data_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setPresenter(new ProspectsListDataBasePresenter(DomainModule.getProspectsBussinesLogicInstance(getCustomSharedPreferences())));
        getPresenter().inject(this, getValidateInternet());

        validateDataBaseCreation(getCustomSharedPreferences().getBoolean(Constants.DATABASE_CREATION_STATE));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_prospectsList) {
            intentToProspectsList();
        } else if (id == R.id.nav_logOut) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void intentToProspectsList(){
        Intent intent = new Intent(this, ProspectListActivity.class);
        startActivityForResult(intent, Constants.DEFAUL_REQUEST_CODE);
        finish();
    }

    private void logOut(){
        getCustomSharedPreferences().addBoolean(Constants.ESTATE_CHECK_KEY,Constants.FALSE);
        getCustomSharedPreferences().addString(Constants.TOKEN,Constants.EMPTY_STRING);
        intentToLoginActivity();
    }
    private void intentToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.DEFAUL_REQUEST_CODE);
        finish();
    }


    @Override
    public void setProspectsListDataBase() {
        getPresenter().validateInternetToGetProspectsList();
    }

    @Override
    public void correctGetProspectsList(List<Prospect> getProspect) {
        if(getProspect != null){
            prospectsElements(getProspect);
            saveDatabaseEstateTrue();
        }
    }

    private void saveDatabaseEstateTrue() {
        getCustomSharedPreferences().addBoolean(Constants.DATABASE_CREATION_STATE,Constants.TRUE);
    }

    private void prospectsElements(List<Prospect> getProspect) {
        for (Prospect prospect : getProspect){
           putProspectsInDataBase(
                   prospect.getId(),
                   prospect.getName(),
                   prospect.getSurname(),
                   prospect.getSchProspectIdentification(),
                   prospect.getTelephone(),
                   prospect.getStatusCd()
           );

        }
    }

    private void putProspectsInDataBase(String id, String name, String surname, String schProspectIdentification, String telephone, int statusCd) {
        getDataBaseHelper().dataBase(id,
                                name,
                                surname,
                                schProspectIdentification,
                                telephone,
                                statusCd);
    }

    private void validateDataBaseCreation(boolean dataBaseCreationState){
        if (!dataBaseCreationState){
            setProspectsListDataBase();
        }
        else {
            showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.dataBaseStateAvaliable);
        }
    }
}

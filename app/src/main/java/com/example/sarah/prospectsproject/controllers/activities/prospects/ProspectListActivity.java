package com.example.sarah.prospectsproject.controllers.activities.prospects;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import com.example.sarah.prospectsproject.controllers.adapters.AdapterProspectsList;
import com.example.sarah.prospectsproject.controllers.views.interfaces_prospectList.IProspectListView;
import com.example.sarah.prospectsproject.dependencyInjection.DomainModule;
import com.example.sarah.prospectsproject.helper.Constants;

import com.example.sarah.prospectsproject.helper.SaveLog;
import com.example.sarah.prospectsproject.presenters.prospectList.ProspectListPresenter;

import java.util.List;

public class ProspectListActivity extends BaseActivity<ProspectListPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, IProspectListView {

    private AdapterProspectsList adapterProspectsList;
    private RecyclerView recyclerViewProspects;
    private boolean isToRefresh;
    private SaveLog saveLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        isToRefresh = false;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        saveLog = new SaveLog();



        setPresenter(new ProspectListPresenter(DomainModule.getProspectsBussinesLogicInstance(getCustomSharedPreferences())));
        getPresenter().inject(this, getValidateInternet());

        loadViews();
        getProspectsFromDataBase();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_logOut) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getProspectsFromDataBase(){
        getPresenter().createThreadToGetProspectsListFromDataBase(this);
    }

    private void showProspectsInAdapter(List<Prospect>prospects){
        adapterProspectsList = new AdapterProspectsList(this,this,prospects);
        recyclerViewProspects.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProspects.setAdapter(adapterProspectsList);
    }

    private void loadViews(){
        recyclerViewProspects = findViewById(R.id.recyclerView_prospectsList);
    }

    @Override
    public void correctGetProspectsListDataBase(final List<Prospect> prospects) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isToRefresh) {
                    adapterProspectsList.addItemOrUpdateItem(prospects);
                }
                else {
                    showProspectsInAdapter(prospects);
                }
            }
        });
    }

    @Override
    public void longClick(final Prospect prospect) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getCustomAlertDialog().showAlertDialog(Constants.user,
                        Constants.TEXT_DELETE_PROSPECT,
                        false, R.string.text_aceptar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                dropProspectFromDataBase(prospect);
                            }
                        }, R.string.text_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, null);
            }
        });
    }

    private void dropProspectFromDataBase(Prospect prospect){
        isToRefresh = true;
        getPresenter().createThreadToDropProspect(prospect, this);
    }
    @Override
    public void inflateProspectInformationLayout(final Prospect prospect) {
        getContentEditProspects().showAlertDialog( null, this, prospect,getPresenter(),false
                , R.string.text_aceptar,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getContentEditProspects().getTextViewContent(prospect);
                        dialogInterface.dismiss();
                    }
                }, R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    @Override
    public void correctDropProspectFromDataBase(Prospect prospect) {
        String textToLog = saveLog.simpleTextToLog(prospect.getId(),prospect.getName(),prospect.getSurname(),prospect.getSchProspectIdentification(),prospect.getTelephone(),prospect.getStatusCd());
        saveLog.appendLog(textToLog);
        showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.text_correct_drop_prospect);
        getPresenter().createThreadToGetProspectsListFromDataBase(this);

    }

    @Override
    public void correctEditProspectFromDataBase() {
        showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.text_correct_edit_prospect);
        isToRefresh = true;
        getProspectsFromDataBase();
    }

    @Override
    public void failedEditProspectFromDataBase() {
        showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.text_failed_edit_prospect);
    }

    @Override
    public void alertFillAllFields() {
        showAlertDialogGeneralInformationOnUiThread(R.string.user, R.string.text_fill_fields);
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
}

package com.example.sarah.prospectsproject.helper;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarah.prospectsproject.R;
import com.example.sarah.prospectsproject.businessModel.Prospect;
import com.example.sarah.prospectsproject.presenters.prospectList.ProspectListPresenter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sarah on 13/09/2018.
 */

public class SetContentEditProspects {

    private final Activity activity;

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextIdentification;
    private EditText editTextTelephone;

    String name;
    String lastName;
    String identification;
    String telephone;

    private Button buttonAccepted;
    private Button buttonCancel;
    private Button buttonDisabled;
    private Button buttonSuccess;
    private Button buttonTime;
    private ImageView imageViewStatus;

    private TextView textViewState;

    private GetImageView getImageView;
    private DataBaseHelper dataBaseHelper;
    private  SaveLog saveLog;
    Context context;

    private ProspectListPresenter prospectListPresenter;

    private int state;
    private String textToLog;

    public SetContentEditProspects(Activity activity) {
        this.activity = activity;
        getImageView = new GetImageView();
        dataBaseHelper = new DataBaseHelper(activity);
        saveLog = new SaveLog();
    }


    public void showAlertDialog(View view, Context context, Prospect prospect, ProspectListPresenter prospectListPresenter ,boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton){
        showAlertDialogNoMessage(view, context, prospect,prospectListPresenter,cancelable,textPositiveButton,onClickListenerPositiveButton,textNegativeButton,onClickListenerNegativeButton);
    }
    private void showAlertDialogNoMessage(View view, Context context, Prospect prospect, ProspectListPresenter prospectListPresenter, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        this.prospectListPresenter = prospectListPresenter;
        this.context = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        view =  inflater.inflate(R.layout.layout_edit_prospect,null);

        if (view != null) {

            getElementsFromEditProspectsLayout(view);
            setTextViews(prospect);
           setListenersToTheControls ();
            builder.setView(view);
        }
        if (onClickListenerNegativeButton != null){
            builder.setNegativeButton(textNegativeButton,onClickListenerNegativeButton);
        }
        if (onClickListenerPositiveButton != null){
            builder.setPositiveButton(textPositiveButton,onClickListenerPositiveButton);
        }

        builder.show();

    }
    private void getElementsFromEditProspectsLayout(View view){
        editTextName= view.findViewById(R.id.editText_name);
        editTextLastName= view.findViewById(R.id.editText_lastName);
        editTextIdentification= view.findViewById(R.id.editText_identification);
        editTextTelephone= view.findViewById(R.id.editText_telephone);


        imageViewStatus= view.findViewById(R.id.imageView_status);

        buttonAccepted= view.findViewById(R.id.button_accepted);
        buttonCancel= view.findViewById(R.id.button_cancelTwo);
        buttonDisabled= view.findViewById(R.id.button_disabled);
        buttonSuccess= view.findViewById(R.id.button_success);
        buttonTime= view.findViewById(R.id.button_pending);


        textViewState = view.findViewById(R.id.textView_state);
    }

    private void setTextViews(Prospect prospect){
        state = prospect.getStatusCd();
        editTextName.setText(prospect.getName());
        editTextLastName.setText(prospect.getSurname());
        editTextIdentification.setText(prospect.getSchProspectIdentification());
        editTextTelephone.setText(prospect.getTelephone());
        imageViewStatus.setImageResource(getImageView.getImageStateView(prospect.getStatusCd()));
    }

    private void setListenersToTheControls(){
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewState.setText(getImageView.getStatusText(Constants.ZERO));
                state = Constants.ZERO;
            }
        });
        buttonSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewState.setText(getImageView.getStatusText(Constants.ONE));
                state = Constants.ONE;
            }
        });

        buttonAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewState.setText(getImageView.getStatusText(Constants.TWO));
                state = Constants.TWO;
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewState.setText(getImageView.getStatusText(Constants.THREE));
                state = Constants.THREE;
            }
        });

        buttonDisabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewState.setText(getImageView.getStatusText(Constants.FOUR));
                state = Constants.FOUR;
            }
        });

    }

    public void getTextViewContent(Prospect prospect){
        name = editTextName.getText().toString().trim();
        lastName = editTextLastName.getText().toString().trim();
        identification = editTextIdentification.getText().toString().trim();
        telephone = editTextTelephone.getText().toString().trim();

        editProspectInfomation(prospect);
    }
    public void editProspectInfomation(Prospect prospect){
        if (validateEmptyFields()){
            prospectListPresenter.alertFillAllFields();

        }
        else {
            textToLog = saveLog.textToLog(prospect.getName(),name,prospect.getSurname(),lastName,prospect.getSchProspectIdentification(),identification,prospect.getTelephone(),telephone,prospect.getStatusCd(),state);
            saveLog.appendLog(textToLog);
            prospectListPresenter.createThreadToEditProspect(prospect,context,prospect.getId(),name,lastName,identification,telephone,state);
        }
    }
    private boolean validateEmptyFields(){
        return editTextName.getText().toString().trim().isEmpty()||editTextLastName.getText().toString().trim().isEmpty()||editTextTelephone.getText().toString().trim().isEmpty() ||editTextIdentification.getText().toString().trim().isEmpty();
    }


}

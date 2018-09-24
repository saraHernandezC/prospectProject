package com.example.sarah.prospectsproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sarah on 12/09/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

     Context context;

    public DataBaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
        this.context = context;
    }

    private static final String tableName = Constants.TABLE_NAME;

    private static final String columnId = Constants.COLUMN_ID;
    private static final String columnName = Constants.COLUMN_NAME;
    private static final String columnLastName = Constants.COLUMN_LAST_NAME;
    private static final String columnTelephone = Constants.COLUMN_TELEPHONE;
    private static final String columnIdentification = Constants.COLUMN_IDENTIFICATION;
    private static final String columnEstate = Constants.COLUMN_ESTATE;
    private static final String sqlWhereClause = Constants.SQL_WHERE_CLAUSE;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constants.SQL_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Constants.SQL_DROP_DATATABLE);
        onCreate(sqLiteDatabase);
    }


    public void dataBase(String id, String name, String surName, String telephone, String schProspectIdentification, int statusCd) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(columnId,id);
        values.put(columnName,name);
        values.put(columnLastName,surName);
        values.put(columnTelephone,telephone);
        values.put(columnIdentification,schProspectIdentification);
        values.put(columnEstate,statusCd);

        dataBase.insert(tableName,null,values);
    }


    public boolean updateData(String id, String name, String surName, String telephone, String schProspectIdentification, int statusCd) {

        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(columnId,id);
        values.put(columnName,name);
        values.put(columnLastName,surName);
        values.put(columnTelephone,telephone);
        values.put(columnIdentification,schProspectIdentification);
        values.put(columnEstate,statusCd);

        dataBase.update(tableName, values, sqlWhereClause, new String[]{id});
        return  true;
    }


    public int deleteData(String id) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        return dataBase.delete(tableName,sqlWhereClause,new String[]{id});
    }


    public Cursor readAllData() {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        return dataBase.rawQuery(Constants.SQL_SELECT_ALL_FROM,null);
    }


}

package com.example.sarah.prospectsproject.helper;

/**
 * Created by sarah on 2/09/2018.
 */

public class Constants {
    public static final String EMPTY_STRING = "";
    public static final int EMPTY_INT = -1;
    public static final String SHARED_PREFERENCES_NAME = "sharedPreferencesName";
    public static final String TOKEN = "Token";
    public static final String TOKEN_SERVICES= "TOKEN";
    public static final long ONE_HUNDRED_AND_TWENTY = 120;
    public static final int UNAUTHORIZED_ERROR_CODE = 401;
    public static final int ERROR_SERVER = 500;
    public static final int SPLASH_SCREEN_TIME = 4000;

    public static final String DEFAUL_ERROR = "Ha ocurrido un error, inténtalo nuevamente.";
    public static final int DEFAUL_REQUEST_CODE = 1;
    public static final int DEFAUL_ERROR_CODE = 0;
    public static final String REQUEST_TIMEOUT_ERROR_MESSAGE = "La solicitud está tardando demasiado. Por favor inténtalo nuevamente.";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final String SPACE =" " ;

    public static final Boolean FALSE = false;
    public static final Boolean TRUE =  true;
    public static final String user = "usuario";
    public static final String ESTATE_CHECK_KEY = "checkState";
    public static final String DATABASE_CREATION_STATE = "dataBaseCreationState";

    public static final boolean IS_DEBUG_LOGIN = false;

    public static final String URL_PROSPECTS = "http://directotesting.igapps.co/";


    //SQL
    public static final String DATABASE_NAME = "prospectos";
    public static final String TABLE_NAME = "prospectosTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_IDENTIFICATION = "identification";
    public static final String COLUMN_ESTATE = "estate";
    public static final String TEXT_TO = " to ";

    public static final String SQL_DATABASE_CREATE = "create table "+TABLE_NAME+
            "(id VARCHAR(20) PRIMARY KEY," +
            "name VARCHAR(20)," +
            "lastName VARCHAR(20)," +
            "identification VARCHAR(30)," +
            "telephone VARCHAR(30)," +
            "estate INTEGER)";
    public static final String SQL_DROP_DATATABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SQL_WHERE_CLAUSE = "ID = ?";
    public static final String SQL_SELECT_ALL_FROM = "select * from "+TABLE_NAME;

    public static final String TEXT_DELETE_PROSPECT = "¿Desea eliminar este prospecto?";

    public static final String TEXT_PENDING = "Pending";
    public static final String TEXT_ACCEPTED = "Accepted";
    public static final String TEXT_CANCEL = "Cancel";
    public static final String TEXT_DISABLED = "Disabled";
    public static final String TEXT_SUCCESS = "Success";

    public static final String TEXT_TO_LOG = "Se ha modificado la siguiente información,";
    public static final String TEXT_TO_DROP_LOG = "Se ha eliminado prospecto con la siguiente información, ";
    public static final String TEXT_DATE_FORMAT = "HH:mm:ss dd/MM/yyyy";
    public static final String TEXT_LOCATION_FILE = "sdcard/log.file";


}

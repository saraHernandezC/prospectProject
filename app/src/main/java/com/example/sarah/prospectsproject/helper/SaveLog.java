package com.example.sarah.prospectsproject.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sarah on 14/09/2018.
 */

public class SaveLog {
    Date date = new Date();
    DateFormat hourdateFormat = new SimpleDateFormat(Constants.TEXT_DATE_FORMAT);
    String dateString = hourdateFormat.format(date);

    public String textToLog( String name1, String name2,String lastName1, String lastName2, String identification1, String identification2, String telephone1, String telephone2, int status1, int status2){
        return dateString+Constants.SPACE+
                Constants.TEXT_TO_LOG+Constants.COLUMN_NAME+Constants.SPACE+name1+Constants.TEXT_TO+name2+
                Constants.SPACE+Constants.COLUMN_LAST_NAME+Constants.SPACE+lastName1+Constants.TEXT_TO+lastName2+
                Constants.SPACE+Constants.COLUMN_IDENTIFICATION+Constants.SPACE+identification1+Constants.TEXT_TO+identification2+
                Constants.SPACE+Constants.COLUMN_TELEPHONE+Constants.SPACE+telephone1+Constants.TEXT_TO+telephone2+
                Constants.COLUMN_ESTATE+Constants.SPACE+Integer.toString(status1)+Constants.TEXT_TO+Integer.toString(status2);
    }

    public String simpleTextToLog(String id, String name, String lastName, String identification, String telephone, int state){
        return dateString+Constants.SPACE+
                Constants.TEXT_TO_DROP_LOG+
                id+Constants.SPACE+name+Constants.SPACE+
                lastName+Constants.SPACE+
                identification+Constants.SPACE+
                telephone+Constants.SPACE+
                Integer.toString(state)+Constants.SPACE;
    }


    public void appendLog(String text)
    {
        File logFile = new File(Constants.TEXT_LOCATION_FILE);
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.append(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

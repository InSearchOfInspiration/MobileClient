package com.golub.golubroman.sentryapp.Occupations.OccupationsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.golub.golubroman.sentry.Occupations.OccupationsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.02.2017.
 */

public class DBQueries{
    protected static String[] NAME_APPOCCUPATIONS = {"Hiking", "Biking", "Walking"};
    protected static int[] ICON_APPOCCUPATIONS = {0x7f02005c, 0x7f02005c, 0x7f02005c};
    protected static String[] BACKGROUND_APPOCCUPATIONS = {"#ffc0cb", "#bbdfe3", "#fb8d8b"};

    protected static int[] USEFULNESS_APPOCCUPATIONS = {5, 5, 5};
    protected static int[] PLEASURE_APPOCCUPATIONS = {5, 5, 5};
    protected static int[] FATIGUE_APPOCCUPATIONS = {5, 5, 5};

    protected static List<String> NAME_PERSONALOCCUPATIONS;
    protected static List<Integer> ICON_PERSONALOCCUPATIONS;
    protected static List<String> BACKGROUND_PERSONALOCCUPATIONS;
    protected static List<Integer> TIME_PERSONALOCCUPATIONS;
    protected static List<Integer> USEFULNESS_PERSONALOCCUPATIONS;
    protected static List<Integer> PLEASURE_PERSONALOCCUPATIONS;
    protected static List<Integer> FATIGUE_PERSONALOCCUPATIONS;


    final static String tableAPPOCCUPATIONS = "AppOccupations";
    final static String tablePERSONALOCCUPATIONS = "PersonalOccupations";
    final static String column_ID = "_ID";
    final static String columnNAME = "name";
    final static String columnICON = "icon";
    final static String columnTIME = "time";
    final static String columnBACKGROUND = "background";
    final static String columnUSEFULNESS = "usefulness";
    final static String columnPLEASURE = "pleasure";
    final static String columnFATIGUE = "fatigue";

    private Context context;
    private static DBHelper dbh;
    private static SQLiteDatabase db;
    private static int appOccupationsCnt = 0;
    private static List<Integer> convertArrayToList(int[] array){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++)
        {
            list.add(array[i]);
        }
        return list;
    }


    public static List<OccupationsModel> getAppOccupations(Context context){
        dbh = new DBHelper(context);
        List<OccupationsModel> occupationsList = new ArrayList<>();
        String querySQL = "SELECT * FROM " + tableAPPOCCUPATIONS;
        db = dbh.getWritableDatabase();

        Cursor cursor = db.rawQuery(querySQL, null);
        if(!cursor.moveToFirst()) {
            return new ArrayList<>();
        }else{occupationsList.add(fillModel(cursor));}
        if(!cursor.moveToNext()) {
            return occupationsList;
        }
        do{
            occupationsList.add(fillModel(cursor));
        }while (cursor.moveToNext());
        cursor.close();
        db.close();
        return occupationsList;
    }
    public static OccupationsModel fillModel(Cursor cursor){
        String name = cursor.getString(cursor.getColumnIndex(columnNAME));
        int icon = cursor.getInt(cursor.getColumnIndex(columnICON));
        String background = cursor.getString(cursor.getColumnIndex(columnBACKGROUND));
        int usefulness = cursor.getInt(cursor.getColumnIndex(columnUSEFULNESS));
        int pleasure = cursor.getInt(cursor.getColumnIndex(columnPLEASURE));
        int fatigue = cursor.getInt(cursor.getColumnIndex(columnFATIGUE));
        int id = cursor.getInt(cursor.getColumnIndex(column_ID));
        appOccupationsCnt++;
        return new OccupationsModel(id, name, background, icon, usefulness, pleasure, fatigue);
    }
    public static void addToAppOccupations(OccupationsModel occupationsModel, Context context){
        dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String name = occupationsModel.getOccupationsName();
        int icon = occupationsModel.getOccupationsIcon();
        String background = occupationsModel.getOccupationsColor();
        int usefulness = occupationsModel.getOccupationsUsefulness();
        int pleasure = occupationsModel.getOccupationsPleasure();
        int fatigue = occupationsModel.getOccupationsFatigue();
        if(occupationsModel.getId() == -1) occupationsModel.setId(appOccupationsCnt + 1);
        cv.put(column_ID, appOccupationsCnt + 1);
        cv.put(columnNAME, name);
        cv.put(columnICON, icon);
        cv.put(columnBACKGROUND, background);
        cv.put(columnUSEFULNESS, usefulness);
        cv.put(columnPLEASURE, pleasure);
        cv.put(columnFATIGUE, fatigue);

        db.insert(tableAPPOCCUPATIONS, null, cv);
        db.close();
    }

    public static void deleteAppOccupations(Context context, int position){
        dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();
        db.delete(tableAPPOCCUPATIONS, column_ID + " = ?", new String[]{Integer.toString(position)});
        db.close();
    }
    /*public static void addData() {
        db = dbh.getWritableDatabase();
        ContentValues cvCAR = new ContentValues();
        ContentValues cvPRICE = new ContentValues();

        *//* Creating class ContentValues object for
            further pushing it in database
            *//*

        for (int i = 0; i < columnNAMES.size(); i++) {
            cvCAR.put(columnNAMES.get(i), addBuffer.get(i));
        }
        cvPRICE.put(columnPRICE, addBuffer.get(columnNAMES.size()));
        db.insert(tableCAR, null, cvCAR);
        db.insert(tablePRICE, null, cvPRICE);
    }*/
}

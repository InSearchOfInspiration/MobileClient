package com.golub.golubroman.sentryapp.Occupations.OccupationsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "sentryDB", null, 1);
    }
    private ContentValues cv;
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



    @Override
    public void onCreate(SQLiteDatabase db) {
        cv = new ContentValues();

        db.execSQL("CREATE TABLE " + tableAPPOCCUPATIONS + " ("
            + column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + columnNAME + " TEXT NOT NULL, "
                + columnICON + " TEXT NOT NULL, "
                + columnBACKGROUND + " TEXT NOT NULL, "
                + columnUSEFULNESS + " INTEGER NOT NULL, "
                + columnPLEASURE + " INTEGER NOT NULL, "
                + columnFATIGUE + " INTEGER NOT NULL"
                    + ");");

        for(int i = 0; i < DBQueries.NAME_APPOCCUPATIONS.length; i++){
            cv.clear();
            cv.put(columnNAME, DBQueries.NAME_APPOCCUPATIONS[i]);
            cv.put(columnICON, DBQueries.ICON_APPOCCUPATIONS[i]);
            cv.put(columnBACKGROUND, DBQueries.BACKGROUND_APPOCCUPATIONS[i]);
            cv.put(columnUSEFULNESS, DBQueries.USEFULNESS_APPOCCUPATIONS[i]);
            cv.put(columnPLEASURE, DBQueries.PLEASURE_APPOCCUPATIONS[i]);
            cv.put(columnFATIGUE, DBQueries.FATIGUE_APPOCCUPATIONS[i]);
            db.insert(tableAPPOCCUPATIONS, null, cv);
        }

        db.execSQL("CREATE TABLE " + tablePERSONALOCCUPATIONS + " ( "
            + column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + columnNAME + " TEXT NOT NULL, "
                + columnICON + " TEXT NOT NULL, "
                + columnBACKGROUND + " TEXT NOT NULL, "
                + columnTIME + " INTEGER NOT NULL, "
                + columnUSEFULNESS + " INTEGER NOT NULL, "
                + columnPLEASURE + " INTEGER NOT NULL, "
                + columnFATIGUE + " INTEGER NOT NULL "
                    + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableAPPOCCUPATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + tablePERSONALOCCUPATIONS);
        onCreate(db);
    }
}

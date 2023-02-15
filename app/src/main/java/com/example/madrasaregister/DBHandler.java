package com.example.madrasaregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends  SQLiteOpenHelper {

    private static final String DATABASE_NAME = "madrasa.db";
    private static final String TABLE_NAME = "student";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLLNO = "roll_no";
    private static final String COLUMN_JOINING = "joining";

    private static final String REC_TABLE_NAME = "record";
    private static final String REC_COLUMN_ID = "id";
    private static final String COLUMN_STD_ID = "std_id";
    private static final String COLUMN_SABAK = "sabak";
    private static final String COLUMN_SABKI = "sabki";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MANZIL = "manzil";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT,"
                + COLUMN_JOINING + " TEXT"
                + ")";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS " + REC_TABLE_NAME + "("
                + REC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_STD_ID + " INTEGER,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_SABAK + " INTEGER,"
                + COLUMN_SABKI + " INTEGER,"
                + COLUMN_MANZIL + " INTEGER,"
                +"FOREIGN KEY ("+COLUMN_STD_ID +")"
                +"REFERENCES student(id)"
                + ")";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS " + REC_TABLE_NAME;
        db.execSQL(sql2);
        onCreate(db);
    }

   
}

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

    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_JOINING, student.getJoining());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                students.add(new Student(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    public void insertRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STD_ID, record.getStdId());
        values.put(COLUMN_DATE, record.getDate());
        values.put(COLUMN_SABAK, record.getSabak());
        values.put(COLUMN_SABKI, record.getSabki());
        values.put(COLUMN_MANZIL, record.getManzil());
        db.insert(REC_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Record> selectAllRecords(Integer id) {
        ArrayList<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM " + REC_TABLE_NAME +" where std_id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                records.add(new Record(cursor.getInt(0),cursor.getInt(1),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4),cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return records;
    }
}

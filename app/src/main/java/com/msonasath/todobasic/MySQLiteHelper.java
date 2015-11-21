package com.msonasath.todobasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.sonasath on 11/19/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "TodoItems.db";
    static final String TABLE = "TodoItemstable";
    static final String ITEM = "TodoItem";
    static final String ID = "_id";
    static boolean useSD = true;

    public static String getPath() {
        if (useSD) {
            return (Environment.getExternalStorageDirectory() + File.separator);
        /*TODO: Use apps own folder for database
         * As of now, we are using sdcard as it is easier to test.
         * It will later be changed to the correct path */
        } else {
            return "/data/data/com.msonasath.todobasic/";
        }
    }

    public MySQLiteHelper(final Context c) {
        super(c, getPath() + DB_NAME, null, 1);
        Log.d("DB", "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "create table " + TABLE + "("
                + ID + " integer primary key autoincrement,"
                + ITEM + " text"
                + ")";
        db.execSQL(DATABASE_CREATE);
        Log.d("DB", "Database CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

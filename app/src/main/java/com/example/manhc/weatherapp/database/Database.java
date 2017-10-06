package com.example.manhc.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manhc on 10/3/2017.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        // Databse: todos_db, Version: 1
        super(context, "weatherDB", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute create table SQL
        db.execSQL("CREATE TABLE weatherDB (_id INTEGER PRIMARY KEY, city TEXT NOT NULL, country TEXT NOT NULL);");
    }

    /**
     * Recreates table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        // DROP table
        db.execSQL("DROP TABLE IF EXISTS weatherDB");
        // Recreate table
        onCreate(db);
    }
}


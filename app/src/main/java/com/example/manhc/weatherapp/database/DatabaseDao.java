package com.example.manhc.weatherapp.database;

/**
 * Created by manhc on 10/3/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manhc.weatherapp.model.Location;


public class DatabaseDao {

    private SQLiteDatabase db;
    private Database dbHelper;

    public DatabaseDao(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // Close the db
    public void close() {
        db.close();
    }

    public void createFavoriteList(int id, String city, String country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("city", city);
        contentValues.put("country", country);
        // Insert into DB
        db.insert("weatherDB", null, contentValues);
    }

    public void deleteLocation(int id) {
        // Delete from DB where id match
        db.delete("weatherDB", "_id = " + id, null);
    }


    public List<Location> getList() {
        List<Location> favaritedList = new ArrayList<Location>();

        // Name of the columns we want to select
        String[] tableColumns = new String[] {"_id","city","country"};

        // Query the database
        Cursor cursor = db.query("weatherDB", tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        // Iterate the results
        while (!cursor.isAfterLast()) {
            Location location = new Location();
            // Take values from the DB
            location.setId(cursor.getInt(0));
            location.setCity(cursor.getString(1));
            location.setCountry(cursor.getString(2));

            // Add to the DB
            favaritedList.add(location);

            // Move to the next result
            cursor.moveToNext();
        }

        return favaritedList;
    }

}

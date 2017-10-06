package com.example.manhc.weatherapp;

import android.content.Context;
import com.example.manhc.weatherapp.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhc on 10/2/2017.
 */

public class CityList {
    public static List<Location> loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.location);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Location> aniList = null;
        try {

            JSONArray JsonArray = new JSONArray(json);
            aniList = new ArrayList<Location>();
            for (int i = 0; i < JsonArray.length(); i++) {
                JSONObject obj = JsonArray.getJSONObject(i);
                Location ani = new Location();
                ani.setId(obj.getInt("_id"));
                ani.setCity(obj.getString("name"));
                ani.setCountry(obj.getString("country"));
                aniList.add(ani);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aniList;
    }


}

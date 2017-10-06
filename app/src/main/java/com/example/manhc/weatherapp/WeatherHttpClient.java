package com.example.manhc.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";


    public String getWeatherData(int id) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + id+"&APPID=fd0bd2a35cf951a258cce35e3f3b1872")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public Bitmap getImage(String code) {
        Bitmap bm =null;
        InputStream is;
        try {
            // Let's read the response
            is = new java.net.URL(IMG_URL +code+".png").openStream();
            bm = BitmapFactory.decodeStream(is);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return bm;

    }

}


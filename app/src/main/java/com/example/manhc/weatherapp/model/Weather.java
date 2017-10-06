package com.example.manhc.weatherapp.model;

/**
 * Created by manhc on 10/2/2017.
 */

import android.graphics.Bitmap;

/**
 * This is a tutorial source code
 * provided "as is" and without warranties.
 *
 * For any question please visit the web site
 * http://www.survivingwithandroid.com
 *
 * or write an email to
 * survivingwithandroid@gmail.com
 *
 */
public class Weather {

    private String status;
    private double tem;
    private double temMax;
    private double temMin;
    private double wind;
    private int pressure;
    private  int humidity;
    private Bitmap iconData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTem() {
        return tem;
    }

    public void setTem(double tem) {
        this.tem = tem;
    }

    public double getTemMax() {
        return temMax;
    }

    public void setTemMax(double temMax) {
        this.temMax = temMax;
    }

    public double getTemMin() {
        return temMin;
    }

    public void setTemMin(double temMin) {
        this.temMin = temMin;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Bitmap getIconData() {
        return iconData;
    }

    public void setIconData(Bitmap iconData) {
        this.iconData = iconData;
    }

}

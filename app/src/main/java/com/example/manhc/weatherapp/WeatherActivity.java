package com.example.manhc.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhc.weatherapp.database.DatabaseDao;
import com.example.manhc.weatherapp.model.Location;
import com.example.manhc.weatherapp.model.Weather;

import org.json.JSONException;

/**
 * Created by manhc on 10/2/2017.
 */

public class WeatherActivity extends Activity {
    private TextView cityText;
    private ImageView imgView;
    private TextView temp;
    private TextView status;
    private TextView tempMax;
    private TextView tempMin;
    private TextView pressure;
    private TextView windSpeed;
    private TextView hum;
    String data;
    private Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityText = (TextView) findViewById(R.id.City);
        status=(TextView) findViewById(R.id.status) ;
        temp = (TextView) findViewById(R.id.tem);
        tempMax = (TextView) findViewById(R.id.temMax);
        tempMin = (TextView) findViewById(R.id.temMin);
        hum = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        windSpeed = (TextView) findViewById(R.id.wind);
        imgView = (ImageView) findViewById(R.id.imageView);
        buttonAdd=(Button) findViewById(R.id.button);
        new JSONWeatherTask().execute();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Toast.makeText(getApplicationContext(), "Information Expired",Toast.LENGTH_LONG).show();
            }
        }, 10000);
        buttonAdd.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatabaseDao data= new DatabaseDao(getApplicationContext());
                Intent intent = getIntent();
                int id=intent.getIntExtra("id",0);
                String city=intent.getStringExtra("city");
                String country=intent.getStringExtra("country");
                data.createFavoriteList(id,city,country);
                Toast.makeText(getApplicationContext(), "Added to Favorite List Successful",Toast.LENGTH_LONG).show();
            }
        });
    }
    private class JSONWeatherTask extends AsyncTask<String, String, Weather> {

        @Override
        protected Weather doInBackground(String... arg0) {
            Intent intent = getIntent();
            int value = intent.getIntExtra("id",0);
            Weather weather = new Weather();

             data = ((new WeatherHttpClient()).getWeatherData((value)));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.setIconData(((new WeatherHttpClient()).getImage(new JSONWeatherParser().image)));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            Intent intent = getIntent();
            String city=intent.getStringExtra("city");
            cityText.setText(city);
            imgView.setImageBitmap(weather.getIconData());
            temp.setText("Temperature: " + Math.round((weather.getTem() - 273.15)) + " \u2103");
            temp.setPadding(5,15,0,15);
            tempMax.setText("TempMax: " + Math.round((weather.getTemMax() - 273.15)) + " \u2103");
            tempMax.setPadding(5,15,0,15);
            tempMin.setText("TempMin: " + Math.round((weather.getTemMin() - 273.15)) + " \u2103");
            tempMin.setPadding(0,15,0,15);
            status.setText("Status: "+weather.getStatus());
            status.setPadding(5,15,0,15);
            hum.setText("Humidity: " + weather.getHumidity() + "%");
            hum.setPadding(5,15,0,15);
            pressure.setText("Pressure: " + weather.getPressure() + " hPa");
            pressure.setPadding(5,15,0,15);
            windSpeed.setText("Wind: " + weather.getWind() + " mps");
            windSpeed.setPadding(5,10,0,10);
        }
    }


    }

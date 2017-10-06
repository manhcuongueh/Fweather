package com.example.manhc.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.manhc.weatherapp.database.DatabaseDao;
import com.example.manhc.weatherapp.model.Location;

import java.util.List;

public class FavoriteList_Activity extends AppCompatActivity {
    FavorAdapter adapter;
    private ListView favoriteList;
    List<Location> locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        favoriteList=(ListView) findViewById(R.id.favorite_List);
        DatabaseDao data= new DatabaseDao(getApplicationContext());
        locationList=data.getList();
        // Pass results to ListViewAdapter Class
        adapter = new FavorAdapter(this, locationList);
        // Binds the Adapter to the ListView
        favoriteList.setAdapter(adapter);
        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Log.d("TAG", "Working");
                Location location = (Location) adapter.getItemAtPosition(position);
                Intent myIntent = new Intent(FavoriteList_Activity.this,WeatherActivity.class);
                myIntent.putExtra("id", location.getId());
                myIntent.putExtra("city", location.getCity());
                myIntent.putExtra("country", location.getCountry());
                // Yêu cầu chạy Example1Activity.
               FavoriteList_Activity.this.startActivity(myIntent);
            }
        });
    }

    }


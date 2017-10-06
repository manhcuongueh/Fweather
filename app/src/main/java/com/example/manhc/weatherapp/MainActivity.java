package com.example.manhc.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.manhc.weatherapp.model.Location;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    List<Location> locationList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate sample data

        locationList = CityList.loadJSONFromAsset(this);

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.cityListView);


        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, locationList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        //Click on list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Location location = (Location) adapter.getItemAtPosition(position);
                Intent myIntent = new Intent(MainActivity.this,WeatherActivity.class);
                myIntent.putExtra("id", location.getId());
                myIntent.putExtra("city", location.getCity());
                myIntent.putExtra("country", location.getCountry());
                // Yêu cầu chạy Example1Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.f_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addtoList:
                Intent myIntent = new Intent(MainActivity.this, FavoriteList_Activity.class);
                MainActivity.this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

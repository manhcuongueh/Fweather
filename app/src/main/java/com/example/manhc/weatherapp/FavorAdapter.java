package com.example.manhc.weatherapp;

/**
 * Created by manhc on 10/3/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.manhc.weatherapp.database.DatabaseDao;
import com.example.manhc.weatherapp.model.Location;

import java.util.ArrayList;
import java.util.List;

public class FavorAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Location> locationList = null;
    private ArrayList<Location> arraylist;
    DatabaseDao data;
    AlertDialog.Builder b;
    public FavorAdapter(Context context, List<Location> locationList) {
        mContext = context;
        this.locationList = locationList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Location>();
        this.arraylist.addAll(locationList);
        data=new DatabaseDao(context);
        b = new AlertDialog.Builder(context);
    }

    public class ViewHolder {
        TextView name;
        Button button;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Location getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.favorite_view, null);

            holder.name = (TextView) view.findViewById(R.id.locationView);
            holder.button=(Button) view.findViewById(R.id.locationDelete);
            holder.button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                    b.setTitle("Alert");
                    b.setMessage("Do you want to delete it");
                    b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            data.deleteLocation(locationList.get(position).getId());
                            locationList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(locationList.get(position).getCity() + " , " + locationList.get(position).getCountry());
        return view;
    }

}


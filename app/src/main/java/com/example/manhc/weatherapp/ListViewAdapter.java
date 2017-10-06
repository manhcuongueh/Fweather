package com.example.manhc.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manhc.weatherapp.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Location> locationList = null;
    private ArrayList<Location> arraylist;

    public ListViewAdapter(Context context, List<Location> locationList) {
        mContext = context;
        this.locationList = locationList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Location>();
        this.arraylist.addAll(locationList);
    }

    public class ViewHolder {
        TextView name;
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
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(locationList.get(position).getCity()+" , "+locationList.get(position).getCountry());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        locationList.clear();
        if (charText.length() == 0) {
            locationList.addAll(arraylist);
        } else {
            for (Location wp : arraylist) {
                String text=wp.getCity().toLowerCase(Locale.getDefault())+","+wp.getCountry().toLowerCase(Locale.getDefault());
                if (text.contains(charText)) {
                    locationList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
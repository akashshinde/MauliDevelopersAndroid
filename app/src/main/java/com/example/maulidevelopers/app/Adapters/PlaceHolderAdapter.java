package com.example.maulidevelopers.app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maulidevelopers.app.Model.IMAGE;
import com.example.maulidevelopers.app.R;

import java.util.ArrayList;

/**
 * Created by Akash on 18/06/14.
 */
public class PlaceHolderAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<IMAGE> data;
    LayoutInflater inflater;
    public PlaceHolderAdapter(ArrayList<IMAGE> deptList) {
        //activity = a;
        data=deptList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vi=view;
        if(view==null)
            vi = inflater.inflate(R.layout.grid_single, null);
        //ImageView image=(ImageView)vi.findViewById(R.id.grid_image);
        //imageLoader.DisplayImage(data.get(position).image_url, image);
        TextView textView = (TextView) vi.findViewById(R.id.textView);
        textView.setText("I am Fiiled");
        return vi;
    }
}

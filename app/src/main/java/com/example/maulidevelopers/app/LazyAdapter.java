package com.example.maulidevelopers.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    public final static String BaseUrl="http://172.20.10.3:3000/flats/all_images.json?flat_id=";
    public final static String BaseUrl2="http://172.20.10.3:3000";
    public String m;

    ArrayList<IMAGE> deptList=new ArrayList<IMAGE>();
    private static final String ARG_SECTION_NUMBER = "section_number";
    public String url;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    public ArrayList<IMAGE> data;
    GridView gridView;
    public LazyAdapter(Context context, ArrayList<IMAGE> deptList) {
        this.gridView = gridView;
        data=deptList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(context.getApplicationContext());

    }


    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null)
        vi = inflater.inflate(R.layout.grid_single, null);
        ImageView image=(ImageView)vi.findViewById(R.id.grid_image);
        imageLoader.DisplayImage(data.get(position).image_url, image);
        return vi;
    }




}
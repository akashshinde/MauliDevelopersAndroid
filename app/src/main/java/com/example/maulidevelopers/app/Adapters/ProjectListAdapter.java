package com.example.maulidevelopers.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maulidevelopers.app.Model.PROJECT;
import com.example.maulidevelopers.app.R;

import java.util.ArrayList;

public class ProjectListAdapter extends ArrayAdapter<PROJECT>{

    private Context context;
    ArrayList<PROJECT> dataObject;
    public ProjectListAdapter(Context context, int textViewResourceId,
                            ArrayList<PROJECT> dataObject) {
        super(context, textViewResourceId, dataObject);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView=convertView;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.list_item_card, parent, false);

        }
        TextView textView = (TextView) rowView.findViewById(R.id.textView1);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView2);
        TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);
        textView3.setText(""+getItem(position).address);
        textView.setText(""+getItem(position).name);
        textView1.setText("("+getItem(position).no_of_flats+" Flats)");

        return rowView;
    }





}

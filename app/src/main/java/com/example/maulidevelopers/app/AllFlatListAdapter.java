package com.example.maulidevelopers.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akash on 17/06/14.
 */
public class AllFlatListAdapter extends ArrayAdapter<FLATS> {


    private Context context;
    ArrayList<FLATS> dataObject;
    public AllFlatListAdapter(Context context, int textViewResourceId,
                              ArrayList<FLATS> dataObject) {
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
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView1);
        TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);
        textView1.setText(""+getItem(position).name);
        textView3.setText(""+getItem(position).status);
        textView2.setText(""+getItem(position).project);

        return rowView;
    }



}

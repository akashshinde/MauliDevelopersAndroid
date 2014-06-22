package com.example.maulidevelopers.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maulidevelopers.app.Model.IMAGE;
import com.example.maulidevelopers.app.R;

import java.util.ArrayList;

/**
 * Created by Akash on 18/06/14.
 */
public class PlaceHolderFragment1 extends ListFragment{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public final static String BaseUrl="http://172.20.10.3:3000/flats/all_images.json?flat_id=";
    public final static String BaseUrl2="http://172.20.10.3:3000";
    public String m;

    ArrayList<IMAGE> deptList=new ArrayList<IMAGE>();
    private static final String ARG_SECTION_NUMBER = "section_number";
    public String url;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */



    public static PlaceHolderFragment1 newInstance(int sectionNumber) {
        PlaceHolderFragment1 fragment = new PlaceHolderFragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceHolderFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_image_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("Filled");
        return rootView;
    }
}


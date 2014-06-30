package com.example.maulidevelopers.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maulidevelopers.app.All_Flats;
import com.example.maulidevelopers.app.R;

/**
 * Created by Akash on 19/06/14.
 */
public class ProjectDetailMainFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    Button button;
    String projectName;
    String projectAddress;
    int projectNoOfFlats;
    public  int project_id;
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProjectDetailMainFragment newInstance(int sectionNumber) {

        ProjectDetailMainFragment fragment = new ProjectDetailMainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectDetailMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.project_details_view_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView1);
        TextView textView1 = (TextView) rootView.findViewById(R.id.textView2);
        TextView textView2 = (TextView) rootView.findViewById(R.id.textView3);
        projectName = getArguments().getString("Project_Name");
        projectAddress = getArguments().getString("Project_Address");
        project_id = getArguments().getInt("Project_id");
        projectNoOfFlats = getArguments().getInt("Project_No_of_flats");
        textView.setText(projectName);
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        textView1.setText(projectAddress);
        textView2.setText(""+projectNoOfFlats);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                        Intent i = new Intent(getActivity(),All_Flats.class);
                        i.putExtra("Project_id",project_id);
                        startActivity(i);
                    }
        return;
                    }
        }





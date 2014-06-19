package com.example.maulidevelopers.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maulidevelopers.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class ProjectDetails extends ActionBarActivity {

    public final static String BaseUrl="http://172.20.10.3:3000/projects/";
    ArrayList<PROJECT> deptList=new ArrayList<PROJECT>();
    public int id;
    Button button;
    String projectName;
    String projectAddress;
    int projectNoOfFlats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        String title = getIntent().getStringExtra("title");
        Bundle i = getIntent().getExtras();
        id = i.getInt("position");
        projectNoOfFlats = i.getInt("Project_No_of_flats");
        projectName = getIntent().getStringExtra("Project_Name");
        projectAddress = getIntent().getStringExtra("Project_Address");
        TextView textView = (TextView) findViewById(R.id.textView1);
        TextView textView1 = (TextView) findViewById(R.id.textView2);
        TextView textView2 = (TextView) findViewById(R.id.textView3);
        textView.setText(projectName);
        textView1.setText(projectAddress);
        textView2.setText(""+projectNoOfFlats);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = id + 1;
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),All_Flats.class);
                i.putExtra("position",(int)id);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

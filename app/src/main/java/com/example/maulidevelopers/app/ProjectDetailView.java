package com.example.maulidevelopers.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import com.example.maulidevelopers.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class ProjectDetailView extends ActionBarActivity {
    public final static String BaseUrl="http://172.20.10.3:3000/projects/";
    public ArrayList<PROJECT> deptList=new ArrayList<PROJECT>();
    public int id;
    //String name = deptList.get(0).name;
    //TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_view);


        String title = getIntent().getStringExtra("title");
        Bundle i = getIntent().getExtras();
        id = i.getInt("position");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = id + 1;
        executeAsyncTask();
        PlaceholderFragment fragment = new PlaceholderFragment();
        //Bundle args = new Bundle();
        //args.putString("title",deptList.get(id).name);
        //fragment.setArguments(args);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    private void executeAsyncTask(){
        Hashtable<String,String> ht=new Hashtable<String,String>();
        GetDeptAyncTask async=new GetDeptAyncTask();

        Hashtable[] ht_array={ht};
        async.execute(ht_array);
    }

    private class GetDeptAyncTask extends AsyncTask<Hashtable<String,String>,Void,String> {

        @Override
        protected String doInBackground(Hashtable<String,String>... params) {
            android.os.Debug.waitForDebugger();
            Hashtable ht=params[0];
            String url = BaseUrl+id+".json";
            String json=HelperHttp.getJSONResponseFromURL(url,ht);
            if(json!=null)
                parseJsonString(deptList,json);

            else{
                return "Invalid Company Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<PROJECT> deptList,String json){
            try {
                JSONArray array=new JSONArray(json);
                for(int i=0;i<array.length();i++){
                    JSONObject j=array.getJSONObject(i);
                    PROJECT d=new PROJECT();
                    d.name=j.optString("name").toString();
                    d.no_of_flats=j.optInt("no_of_flats");
                    d.address = j.optString("address");
                    deptList.add(d);
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        @Override
        protected void onPostExecute(String result){

            if(result=="SUCCESS")
            {

            }
            else{}
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_detail_view, menu);
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        //Bundle args = getArguments();
        TextView textView;
        String name;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            name = getArguments().getString("title");
            View rootView = inflater.inflate(R.layout.fragment_project_detail_view, container, false);
            textView = (TextView) rootView.findViewById(R.id.textView1);
            textView.setText(name);

            return rootView;
        }
    }
}

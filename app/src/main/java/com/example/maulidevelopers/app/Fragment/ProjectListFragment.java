package com.example.maulidevelopers.app.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maulidevelopers.app.Library.HelperHttp;
import com.example.maulidevelopers.app.Model.PROJECT;
import com.example.maulidevelopers.app.Adapters.ProjectListAdapter;
import com.example.maulidevelopers.app.ProjectView;
import com.example.maulidevelopers.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Akash on 14/06/14.
 */
public class ProjectListFragment extends ListFragment {

    public final static String BaseUrl="http://limitless-spire-2426.herokuapp.com/projects.json";
    ArrayList<PROJECT> deptList=new ArrayList<PROJECT>();

    public ProjectListFragment() {
    }

    private void executeAsyncTask(){
        Hashtable<String,String> ht=new Hashtable<String,String>();
        GetDeptAyncTask async=new GetDeptAyncTask();

        Hashtable[] ht_array={ht};
        async.execute(ht_array);
    }

    private class GetDeptAyncTask extends AsyncTask<Hashtable<String,String>,Void,String> {

        private ProgressDialog progressDialog;
        private GetDeptAyncTask() {
            this.progressDialog = new ProgressDialog(getActivity());
            this.progressDialog.setCancelable(false);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.progressDialog.setMessage("Loading .....");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.show();
        }

        @Override
        protected String doInBackground(Hashtable<String,String>... params) {

            Hashtable ht=params[0];

            String json= HelperHttp.getJSONResponseFromURL(BaseUrl, ht);
            System.out.print("Networks working properly");
            if(json!=null) parseJsonString(deptList,json);
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
                    d.floors = j.optString("floor_size");
                    d.id = j.optInt("id");
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
                //Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                if (this.progressDialog.isShowing()) {
                    this.progressDialog.dismiss();
                }
                setListAdapter(new ProjectListAdapter(getActivity(), R.layout.list_item_card, deptList));
            }
            else{}
        }

    }

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static ProjectListFragment newInstance(int sectionNumber) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.listview, container, false);
        //TextView textView = (TextView) layoutView.findViewById(R.id.textView1);
        return layoutView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeAsyncTask();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


            Intent i = new Intent(getActivity(),ProjectView.class);
            String title = deptList.get(position).name;
            i.putExtra("title",title);
            i.putExtra("Project_Name",deptList.get(position).name);
            i.putExtra("Project_Address",deptList.get(position).address);
            i.putExtra("Project_No_of_flats",deptList.get(position).no_of_flats);
            i.putExtra("Project_id",deptList.get(position).id);
            startActivity(i);
    }
}

package com.example.maulidevelopers.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Akash on 17/06/14.
 */
public class ProjectImageGridFragment extends Fragment {

    public final static String BaseUrl="http://172.20.10.3:3000/flats/all_images.json?flat_id=";
    public final static String BaseUrl2="http://172.20.10.3:3000";
    public String m;
    ArrayList<IMAGE> deptList=new ArrayList<IMAGE>();
    private static final String ARG_SECTION_NUMBER = "section_number";
    public String url;


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
            url = BaseUrl+1;
            String json=HelperHttp.getJSONResponseFromURL(url,ht);
            if(json!=null) parseJsonString(deptList,json);
            else{
                return "Invalid Company Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<IMAGE> deptList,String json){
            try {
                JSONArray array=new JSONArray(json);
                for(int i=0;i<array.length();i++){
                    JSONObject j=array.getJSONObject(i);
                    IMAGE d=new IMAGE();
                    m = j.optString("url").toString();;
                    d.image_url= BaseUrl2+m;
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
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                GridView gridView = (GridView) getActivity().findViewById(R.id.gridView);
                LazyAdapter adapter = new LazyAdapter(getActivity(),deptList);
                gridView.setAdapter(adapter);
                //
            }

            else{}
        }
    }
    public static GridImageViewFragment newInstance(int sectionNumber) {
        GridImageViewFragment fragment = new GridImageViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.project_gallery_view,container,false);
        GridView gridView = (GridView) vi.findViewById(R.id.gridView);
        executeAsyncTask();

        //adapter.notifyDataSetChanged();
        return vi;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
package com.example.maulidevelopers.app.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.maulidevelopers.app.Library.HelperHttp;
import com.example.maulidevelopers.app.Model.IMAGE;
import com.example.maulidevelopers.app.Adapters.LazyAdapter;
import com.example.maulidevelopers.app.PhotoView;
import com.example.maulidevelopers.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Akash on 17/06/14.
 */
public class GridImageViewFragment extends Fragment {

    public final static String BaseUrl="http://limitless-spire-2426.herokuapp.com/flats/all_images.json?flat_id=";
    public final static String BaseUrl2="http://limitless-spire-2426.herokuapp.com";
    public String m;
    public int flat_id;
    ArrayList<IMAGE> deptList=new ArrayList<IMAGE>();
    private static final String ARG_SECTION_NUMBER = "section_number";
    public String url;



    private void executeAsyncTask(){
        Hashtable<String,String> ht=new Hashtable<String,String>();
        GridView gridView = (GridView) getActivity().findViewById(R.id.gridView);
        GetDeptAyncTask async=new GetDeptAyncTask();
        Hashtable[] ht_array={ht};
        async.execute(ht_array);
    }



    private class GetDeptAyncTask extends AsyncTask<Hashtable<String,String>,Void,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Hashtable<String,String>... params) {

            Hashtable ht=params[0];
            int id = getArguments().getInt("flat_id");
            url = BaseUrl+id;
            String json= HelperHttp.getJSONResponseFromURL(url, ht);
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
                if (deptList.isEmpty()) {

                }
                else{
                    GridView gridView = (GridView) getActivity().findViewById(R.id.gridView);
                    LazyAdapter adapter = new LazyAdapter(getActivity(),deptList);
                    gridView.setAdapter(adapter);
                    //
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String image_url = deptList.get(i).image_url;
                            String details  = deptList.get(i).details;
                            Intent im = new Intent(getActivity(),PhotoView.class);
                            im.putExtra("image_url",image_url);
                            im.putExtra("details",details);
                            startActivity(im);
                        }
                    });
                }


            }

            else{}
        }
    }
    public static GridImageViewFragment newInstance(int sectionNumber,int flat_id) {
        GridImageViewFragment fragment = new GridImageViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt("flat_id",flat_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.grid_image_fragment,container,false);
        GridView gridView = (GridView) vi.findViewById(R.id.gridView);
        if(deptList.isEmpty())
        {
            vi = inflater.inflate(R.layout.fragment_empty_view,container,false);
        }
        executeAsyncTask();

        //adapter.notifyDataSetChanged();
        return vi;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

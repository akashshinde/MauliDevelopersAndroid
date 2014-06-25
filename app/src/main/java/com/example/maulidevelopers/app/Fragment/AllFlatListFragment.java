package com.example.maulidevelopers.app.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maulidevelopers.app.Adapters.AllFlatListAdapter;
import com.example.maulidevelopers.app.Model.FLATS;
import com.example.maulidevelopers.app.Library.HelperHttp;
import com.example.maulidevelopers.app.R;
import com.example.maulidevelopers.app.SingleFlatView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Akash on 17/06/14.
 */
public class AllFlatListFragment extends ListFragment {

    public final static String BaseUrl="http://limitless-spire-2426.herokuapp.com/flats/all_flats.json?id=";
    ArrayList<FLATS> deptList=new ArrayList<FLATS>();
    public String url;
    public int flat_id;

    public AllFlatListFragment(int id) {
        url = BaseUrl+id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.listview, container, false);
        return layoutView;
    }

    private void executeAsyncTask(){
        Hashtable<String,String> ht=new Hashtable<String,String>();
        GetDeptAyncTask async=new GetDeptAyncTask();

        Hashtable[] ht_array={ht};
        async.execute(ht_array);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        executeAsyncTask();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        {
            Intent i = new Intent(getActivity(),SingleFlatView.class);
            String title = deptList.get(position).name;
            i.putExtra("title",title);
            i.putExtra("name",deptList.get(position).name);
            i.putExtra("hall_size",deptList.get(position).hall_size);
            i.putExtra("bedroom_size",deptList.get(position).bedroom_size);
            i.putExtra("kitchen_size",deptList.get(position).kitchen_size);
            i.putExtra("saleble_area",deptList.get(position).saleble_area);
            i.putExtra("flat_id",deptList.get(position).flat_id);
            i.putExtra("info",deptList.get(position).info);
            i.putExtra("position",(int)position);
            startActivity(i);
        }
    }

    private class GetDeptAyncTask extends AsyncTask<Hashtable<String,String>,Void,String> {

        @Override
        protected String doInBackground(Hashtable<String,String>... params) {
            android.os.Debug.waitForDebugger();

            Hashtable ht=params[0];

            String json= HelperHttp.getJSONResponseFromURL(url, ht);
            if(json!=null) parseJsonString(deptList,json);
            else{
                return "Invalid Company Id";
            }
            return "SUCCESS";
        }

        protected void parseJsonString(ArrayList<FLATS> deptList,String json){
            try {
                JSONArray array=new JSONArray(json);
                for(int i=0;i<array.length();i++){
                    JSONObject j=array.getJSONObject(i);
                    FLATS d=new FLATS();
                    d.name=j.optString("name").toString();
                    d.project=j.optInt("project_id");
                    d.status = j.optString("status");
                    d.bedroom_size = j.optString("bed_size");
                    d.hall_size = j.optString("hall_size");
                    d.kitchen_size = j.optString("kitchen_size");
                    d.status = j.optString("status");
                    d.saleble_area = j.optString("salable_area");
                    d.floor = j.optString("floor");
                    d.flat_id = j.optInt("id");
                    d.info = j.optString("info");
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
                setListAdapter(new AllFlatListAdapter(getActivity(), R.layout.list_item_card, deptList));
            }
            else{}
        }

    }


}

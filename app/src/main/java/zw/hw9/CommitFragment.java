package zw.hw9;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import zw.hw9.zw.hw9.models.BillModel;
import zw.hw9.zw.hw9.models.CommitModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommitFragment extends Fragment implements TabHost.OnTabChangeListener {
    private String[] house = {"http://congressinfo.us-west-2.elasticbeanstalk.com/?symbol=houseC"};
    private String[] senate = {"http://congressinfo.us-west-2.elasticbeanstalk.com/?symbol=senateC"};
    private String[] joint = {"http://congressinfo.us-west-2.elasticbeanstalk.com/?symbol=jointC"};
    ListView houseList;
    ListView senateList;
    ListView jointList;

    public CommitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_commit, container, false);
        TabHost tbHost = (TabHost) layout.findViewById(R.id.tabHostCommit);
        tbHost.setup();

        TabHost.TabSpec spec = tbHost.newTabSpec("Tab One");
        spec.setContent(R.id.committab1);
        spec.setIndicator("HOUSE");
        tbHost.addTab(spec);

        //Tab 2
        spec = tbHost.newTabSpec("Tab Two");
        spec.setContent(R.id.committab2);
        spec.setIndicator("SENATE");
        tbHost.addTab(spec);

        //Tab 3
        spec = tbHost.newTabSpec("Tab Three");
        spec.setContent(R.id.committab3);
        spec.setIndicator("JOINT");
        tbHost.addTab(spec);
        tbHost.setCurrentTab(0);
        tbHost.setOnTabChangedListener(this);

        houseList = (ListView) layout.findViewById(R.id.lvhouse);
        senateList = (ListView) layout.findViewById(R.id.lvsenate);
        jointList = (ListView) layout.findViewById(R.id.lvjoint);
        houseList.setOnItemClickListener(onListClick);
        senateList.setOnItemClickListener(onListClick);
        jointList.setOnItemClickListener(onListClick);
        new houseTask().execute(house);
        new senateTask().execute(senate);
        new jointTask().execute(joint);

        return layout;
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(),commitDetailActivity.class);
            CommitModel cm =(CommitModel) adapterView.getAdapter().getItem(i);
            intent.putExtra("commit",cm);
            startActivity(intent);
        }
    };

    class houseTask extends AsyncTask<String, String, List<CommitModel>> {
        BufferedReader bf;
        HttpURLConnection connection;


        @Override
        protected List<CommitModel> doInBackground(String... strings) {
            List<CommitModel> cModelList = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                bf = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray results = new JSONObject(buffer.toString()).getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    CommitModel cm = new CommitModel();
                    JSONObject commit = results.getJSONObject(i);
                    cm.setId(commit.getString("committee_id"));
                    cm.setChamber(commit.getString("chamber"));
                    cm.setName(commit.getString("name"));
                    if(commit.has("parent_committee_id")&&!commit.isNull("parent_committee_id")) {
                        cm.setParent(commit.getString("parent_committee_id"));
                    }else{
                        cm.setParent("N.A.");
                    }
                    if(commit.has("office")&&!commit.isNull("office")) {
                        cm.setOffice(commit.getString("office"));
                    }else{
                        cm.setOffice("N.A.");
                    }
                    if(commit.has("phone")&&!commit.isNull("phone")) {
                        cm.setContact(commit.getString("phone"));
                    }else{
                        cm.setContact("N.A.");
                    }
                    cModelList.add(cm);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null) {
                    connection.disconnect();
                }
                try {
                    if(bf!=null) bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cModelList;

        }

        @Override
        protected void onPostExecute(List<CommitModel> commitModels) {
            super.onPostExecute(commitModels);
            Activity myActivity = getActivity();
            if (myActivity==null) return;
            CommitAdapter adapter = new CommitAdapter(myActivity.getApplicationContext(),R.layout.commitrow,commitModels);
            adapter.sort(new Comparator<CommitModel>() {
                @Override
                public int compare(CommitModel l1, CommitModel l2) {
                    String s1 = l1.getName(), s2 = l2.getName();
                    return s1.compareTo(s2);

                }
            });
            houseList.setAdapter(adapter);
        }
    }
    class senateTask extends AsyncTask<String, String, List<CommitModel>> {
        BufferedReader bf;
        HttpURLConnection connection;


        @Override
        protected List<CommitModel> doInBackground(String... strings) {
            List<CommitModel> cModelList = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                bf = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray results = new JSONObject(buffer.toString()).getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    CommitModel cm = new CommitModel();
                    JSONObject commit = results.getJSONObject(i);
                    cm.setId(commit.getString("committee_id"));
                    cm.setChamber(commit.getString("chamber"));
                    cm.setName(commit.getString("name"));
                    if(commit.has("parent_committee_id")&&!commit.isNull("parent_committee_id")) {
                        cm.setParent(commit.getString("parent_committee_id"));
                    }else{
                        cm.setParent("N.A.");
                    }
                    if(commit.has("office")&&!commit.isNull("office")) {
                        cm.setOffice(commit.getString("office"));
                    }else{
                        cm.setOffice("N.A.");
                    }
                    if(commit.has("contact")&&!commit.isNull("contact")) {
                        cm.setContact(commit.getString("contact"));
                    }else{
                        cm.setContact("N.A.");
                    }
                    cModelList.add(cm);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null) {
                    connection.disconnect();
                }
                try {
                    if(bf!=null) bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cModelList;

        }

        @Override
        protected void onPostExecute(List<CommitModel> commitModels) {
            super.onPostExecute(commitModels);
            Activity myActivity = getActivity();
            if (myActivity==null) return;
            CommitAdapter adapter = new CommitAdapter(myActivity.getApplicationContext(),R.layout.commitrow,commitModels);
            adapter.sort(new Comparator<CommitModel>() {
                @Override
                public int compare(CommitModel l1, CommitModel l2) {
                    String s1 = l1.getName(), s2 = l2.getName();
                    return s1.compareTo(s2);

                }
            });
            senateList.setAdapter(adapter);
        }
    }
    class jointTask extends AsyncTask<String, String, List<CommitModel>> {
        BufferedReader bf;
        HttpURLConnection connection;


        @Override
        protected List<CommitModel> doInBackground(String... strings) {
            List<CommitModel> cModelList = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                bf = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray results = new JSONObject(buffer.toString()).getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    CommitModel cm = new CommitModel();
                    JSONObject commit = results.getJSONObject(i);
                    cm.setId(commit.getString("committee_id"));
                    cm.setChamber(commit.getString("chamber"));
                    cm.setName(commit.getString("name"));
                    if(commit.has("parent_committee_id")&&!commit.isNull("parent_committee_id")) {
                        cm.setParent(commit.getString("parent_committee_id"));
                    }else{
                        cm.setParent("N.A.");
                    }
                    if(commit.has("office")&&!commit.isNull("office")) {
                        cm.setOffice(commit.getString("office"));
                    }else{
                        cm.setOffice("N.A.");
                    }
                    if(commit.has("contact")&&!commit.isNull("contact")) {
                        cm.setContact(commit.getString("contact"));
                    }else{
                        cm.setContact("N.A.");
                    }
                    cModelList.add(cm);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null) {
                    connection.disconnect();
                }
                try {
                    if(bf!=null) bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cModelList;

        }

        @Override
        protected void onPostExecute(List<CommitModel> commitModels) {
            super.onPostExecute(commitModels);
            Activity myActivity = getActivity();
            if (myActivity==null) return;
            CommitAdapter adapter = new CommitAdapter(myActivity.getApplicationContext(),R.layout.commitrow,commitModels);
            adapter.sort(new Comparator<CommitModel>() {
                @Override
                public int compare(CommitModel l1, CommitModel l2) {
                    String s1 = l1.getName(), s2 = l2.getName();
                    return s1.compareTo(s2);

                }
            });
            jointList.setAdapter(adapter);
        }
    }
    class CommitAdapter extends ArrayAdapter{
        private List<CommitModel> commitList;
        private int resource;
        private LayoutInflater inflater;
        private Context context;
        public CommitAdapter(Context context, int resource, List<CommitModel> objects) {
            super(context, resource, objects);
            commitList= objects;
            this.resource =resource;
            this.context=context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = inflater.inflate(resource,null);
            }
            TextView tvid = (TextView)convertView.findViewById(R.id.cdeid);
            TextView tvname = (TextView)convertView.findViewById(R.id.cdename);
            TextView tvchamber = (TextView)convertView.findViewById(R.id.cdechamber);
            tvid.setText(commitList.get(position).getId().toUpperCase());
            tvname.setText(commitList.get(position).getName());
            String temp = commitList.get(position).getChamber();
            String chamber =   temp.substring(0,1).toUpperCase() + temp.substring(1);
            tvchamber.setText(chamber);
                return convertView;
        }
    }

    @Override
    public void onTabChanged (String s){

    }
}
package zw.hw9;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.util.List;

import zw.hw9.zw.hw9.models.BillModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillsFragment extends Fragment implements TabHost.OnTabChangeListener {
    private String[] activeUrl ={"http://104.198.0.197:8080/bills?history.active=true&apikey=74b463c521c84ca5b7dd3d30ac0417f5&per_page=50"};
    private String[] newUrl ={"http://104.198.0.197:8080/bills?history.active=false&apikey=74b463c521c84ca5b7dd3d30ac0417f5&per_page=50"};
    ListView activeList;
    ListView newList;
    public BillsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        RelativeLayout layout=(RelativeLayout) inflater.inflate(R.layout.fragment_bills,container,false);
        TabHost tbHost =(TabHost)layout.findViewById(R.id.tabHostBill);
        tbHost.setup();

        TabHost.TabSpec spec = tbHost.newTabSpec("Tab One");
        spec.setContent(R.id.billtab1);
        spec.setIndicator("ACTIVE BILLS");
        tbHost.addTab(spec);

        //Tab 2
        spec = tbHost.newTabSpec("Tab Two");
        spec.setContent(R.id.billtab2);
        spec.setIndicator("NEW BILLS");
        tbHost.addTab(spec);

        tbHost.setCurrentTab(0);
        tbHost.setOnTabChangedListener(this);
        activeList=(ListView)layout.findViewById(R.id.lvactive);
        newList =(ListView)layout.findViewById(R.id.lvnew);
        activeList.setOnItemClickListener(onListClick);
        newList.setOnItemClickListener(onListClick);
        new activeTask().execute(activeUrl);
        new newTask().execute(newUrl);
        return layout;

    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(getActivity(),billDetailActivity.class);
            BillModel bm =(BillModel) adapterView.getAdapter().getItem(i);
            intent.putExtra("bill",bm);
            startActivity(intent);
        }
    };
    class activeTask extends AsyncTask<String,Void,List<BillModel>>{
        BufferedReader bf;
        HttpURLConnection connection;
        @Override
        protected List<BillModel> doInBackground(String... strings) {
            List<BillModel> bmList = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                bf= new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }


                JSONArray results = new JSONObject(buffer.toString()).getJSONArray("results");

                for(int i = 0 ;i < results.length();i++){
                    JSONObject bill = results.getJSONObject(i);
                    BillModel bm = new BillModel();
                    bm.setId(bill.getString("bill_id"));
                    bm.setIntro(bill.getString("introduced_on"));
                    bm.setTitile(bill.getString("official_title"));
                    bm.setType(bill.getString("bill_type"));
                    JSONObject sponsor= bill.getJSONObject("sponsor");
                    String spons = sponsor.getString("title")+". "+sponsor.getString("last_name")+sponsor.getString("first_name");
                    bm.setSponsor(spons);
                    bm.setChamber(bill.getString("chamber"));
                    bm.setStatus("Active");
                    JSONObject urls= bill.getJSONObject("urls");
                    bm.setCurl(urls.getString("congress"));
                    if(bill.has("last_version")&&!bill.isNull("last_version")) {
                        JSONObject lastVersion = bill.getJSONObject("last_version");
                        bm.setVstatus(lastVersion.getString("version_name"));
                        if(lastVersion.has("urls")&&!bill.isNull("urls")) {
                            JSONObject pdf = lastVersion.getJSONObject("urls");
                            bm.setBurl(pdf.getString("pdf"));
                        }else{
                            bm.setBurl("N.A.");
                        }
                    }else{
                        bm.setVstatus("N.A.");
                        bm.setBurl("N.A.");
                    }


                    bmList.add(bm);
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
            return bmList;
        }

        @Override
        protected void onPostExecute(List<BillModel> billModels) {
            BillAdapter adapter = new BillAdapter(getActivity().getApplicationContext(), R.layout.billrow,billModels);
            activeList.setAdapter(adapter);

        }
    }
    class newTask extends AsyncTask<String,Void,List<BillModel>>{
        BufferedReader bf;
        HttpURLConnection connection;
        @Override
        protected List<BillModel> doInBackground(String... strings) {
            List<BillModel> bmList = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                bf= new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }


                JSONArray results = new JSONObject(buffer.toString()).getJSONArray("results");

                for(int i = 0 ;i < results.length();i++){
                    JSONObject bill = results.getJSONObject(i);
                    BillModel bm = new BillModel();
                    bm.setId(bill.getString("bill_id"));
                    bm.setIntro(bill.getString("introduced_on"));
                    bm.setTitile(bill.getString("official_title"));
                    bm.setType(bill.getString("bill_type"));
                    JSONObject sponsor= bill.getJSONObject("sponsor");
                    String spons = sponsor.getString("title")+". "+sponsor.getString("last_name")+sponsor.getString("first_name");
                    bm.setSponsor(spons);
                    bm.setChamber(bill.getString("chamber"));
                    bm.setStatus("New");
                    JSONObject urls= bill.getJSONObject("urls");
                    bm.setCurl(urls.getString("congress"));
                    if(bill.has("last_version")&&!bill.isNull("last_version")) {
                        JSONObject lastVersion = bill.getJSONObject("last_version");
                        bm.setVstatus(lastVersion.getString("version_name"));
                        if(lastVersion.has("urls")&&!bill.isNull("urls")) {
                            JSONObject pdf = lastVersion.getJSONObject("urls");
                            bm.setBurl(pdf.getString("pdf"));
                        }else{
                            bm.setBurl("N.A.");
                        }
                    }else{
                        bm.setVstatus("N.A.");
                        bm.setBurl("N.A.");
                    }

                    bmList.add(bm);
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
            return bmList;
        }

        @Override
        protected void onPostExecute(List<BillModel> billModels) {
            BillAdapter adapter = new BillAdapter(getActivity().getApplicationContext(), R.layout.billrow,billModels);
            newList.setAdapter(adapter);

        }
    }
    class BillAdapter extends ArrayAdapter {
        private List<BillModel> billList;
        private int resource;
        private LayoutInflater inflater;
        private Context context;

        public BillAdapter(Context context, int resource, List<BillModel> objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.context = context;
            billList = objects;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }
            TextView tvid, tvintro,tvtitle;
            tvid =(TextView)convertView.findViewById(R.id.bdeid);
            tvintro =(TextView)convertView.findViewById(R.id.bdeintro);
            tvtitle =(TextView)convertView.findViewById(R.id.bdetitle);
            tvid.setText(billList.get(position).getId().toUpperCase());
            tvintro.setText(billList.get(position).getIntro());
            tvtitle.setText(billList.get(position).getTitile());
            return convertView;
        }
    }

    @Override
    public void onTabChanged(String s) {

    }
}

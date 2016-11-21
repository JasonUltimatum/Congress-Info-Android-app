package zw.hw9;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LegisFragment extends Fragment implements TabHost.OnTabChangeListener {
    private RelativeLayout layout;
    private String startUrl = "http://104.198.0.197:8080/legislators?apikey=74b463c521c84ca5b7dd3d30ac0417f5&per_page=all";
    ListView mainList;
    private String[] texts ={"I","KNOW","YOU","SOME","WHERE","OUT","THERE"};
    public LegisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout=(RelativeLayout) inflater.inflate(R.layout.fragment_legis,container,false);
         TabHost tbHost =(TabHost)layout.findViewById(R.id.tabHost);
        tbHost.setup();

        TabHost.TabSpec spec = tbHost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("BY STATES");
        tbHost.addTab(spec);

        //Tab 2
        spec = tbHost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("HOUSE");
        tbHost.addTab(spec);

        //Tab 3
        spec = tbHost.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("SENATE");
        tbHost.addTab(spec);
        tbHost.setCurrentTab(0);
        tbHost.setOnTabChangedListener(this);

        mainList = (ListView)layout.findViewById(R.id.listviewstate);
        mainList.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
        return layout;
    }
    class MyTask extends AsyncTask<Void,String,Void>{
        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
           adapter =(ArrayAdapter<String>)mainList.getAdapter();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(String item:texts){
                publishProgress(item);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
           adapter.add(values[0]);

        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
    @Override
    public void onTabChanged(String s) {

    }
}

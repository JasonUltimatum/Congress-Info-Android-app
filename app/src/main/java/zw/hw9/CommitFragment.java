package zw.hw9;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommitFragment extends Fragment implements TabHost.OnTabChangeListener {


    public CommitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout=(RelativeLayout) inflater.inflate(R.layout.fragment_commit,container,false);
        TabHost tbHost =(TabHost)layout.findViewById(R.id.tabHostCommit);
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
        return layout;
    }

    @Override
    public void onTabChanged(String s) {

    }
}

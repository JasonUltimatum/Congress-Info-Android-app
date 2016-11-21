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
public class BillsFragment extends Fragment implements TabHost.OnTabChangeListener {


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
        return layout;

    }

    @Override
    public void onTabChanged(String s) {

    }
}

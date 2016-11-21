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
public class FavFragment extends Fragment implements TabHost.OnTabChangeListener {


    public FavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout=(RelativeLayout) inflater.inflate(R.layout.fragment_fav,container,false);
        TabHost tbHost =(TabHost)layout.findViewById(R.id.tabHostFav);
        tbHost.setup();

        TabHost.TabSpec spec = tbHost.newTabSpec("Tab One");
        spec.setContent(R.id.favtab1);
        spec.setIndicator("LEGISLATORS");
        tbHost.addTab(spec);

        //Tab 2
        spec = tbHost.newTabSpec("Tab Two");
        spec.setContent(R.id.favtab2);
        spec.setIndicator("BILLS");
        tbHost.addTab(spec);
        spec = tbHost.newTabSpec("Tab Three");
        spec.setContent(R.id.favtab3);
        spec.setIndicator("COMMITTEES");
        tbHost.addTab(spec);
        tbHost.setCurrentTab(0);
        tbHost.setOnTabChangedListener(this);
        return layout;
    }

    @Override
    public void onTabChanged(String s) {

    }
}

package zw.hw9;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import zw.hw9.zw.hw9.models.BillModel;
import zw.hw9.zw.hw9.models.CommitModel;
import zw.hw9.zw.hw9.models.LegislatorModel;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment implements TabHost.OnTabChangeListener, View.OnClickListener {
    ListView lvBill;
    ListView lvLeg;
    ListView lvCommit;
    HashMap<String, Integer> map;
    LegisFragment.LegislatorAdapter legislatorAdapter;
    BillsFragment.BillAdapter billAdapter;
    CommitFragment.CommitAdapter commitAdapter;

    public FavFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        legislatorAdapter.clear();
        legislatorAdapter.notifyDataSetChanged();
        billAdapter.clear();
        billAdapter.notifyDataSetChanged();
        commitAdapter.clear();
        commitAdapter.notifyDataSetChanged();

        Gson gson = new Gson();

        ArrayList<LegislatorModel> lmList = new ArrayList<>();
        SharedPreferences sharedLeg = getActivity().getSharedPreferences("Leg_Fav", MODE_PRIVATE);
        Set<String> favLeg = sharedLeg.getStringSet("favLegItem", new HashSet<String>());
        Iterator<String> itLeg = favLeg.iterator();
        while (itLeg.hasNext()) {
            String temp = itLeg.next();
            lmList.add(gson.fromJson(temp, LegislatorModel.class));
        }
        LegisFragment l = new LegisFragment();
        legislatorAdapter = l.new LegislatorAdapter(getActivity().getApplicationContext(), R.layout.legrow, lmList);
        legislatorAdapter.sort(new Comparator<LegislatorModel>() {
            @Override
            public int compare(LegislatorModel l1, LegislatorModel l2) {
                String s1 = l1.getName(), s2 = l2.getName();
                int i1 = s1.indexOf(','), i2 = s2.indexOf(',');
                return s1.substring(0, i1).compareTo(s2.substring(0, i2));

            }
        });
        lvLeg.setAdapter(legislatorAdapter);
        getLegIndex(legislatorAdapter.legList);
        LinearLayout sideLayout = (LinearLayout) getActivity().findViewById(R.id.favlegside);
        displayIndex(sideLayout);


        ArrayList<BillModel> bmList = new ArrayList<>();
        SharedPreferences shared = getActivity().getSharedPreferences("Bill_Fav", MODE_PRIVATE);
        Set<String> favBill = shared.getStringSet("favBillItem", new HashSet<String>());
        Iterator<String> itBill = favBill.iterator();
        while (itBill.hasNext()) {
            String temp = itBill.next();
            bmList.add(gson.fromJson(temp, BillModel.class));
        }
        BillsFragment b = new BillsFragment();
        billAdapter = b.new BillAdapter(getActivity().getApplicationContext(), R.layout.billrow, bmList);
        billAdapter.sort(new Comparator<BillModel>() {
            @Override
            public int compare(BillModel l1, BillModel l2) {
                String s1 = l1.getIntro(), s2 = l2.getIntro();
                return s2.compareTo(s1);

            }
        });
        lvBill.setAdapter(billAdapter);

        ArrayList<CommitModel> cmList = new ArrayList<>();
        SharedPreferences sharedCommit = getActivity().getSharedPreferences("Commit_Fav", MODE_PRIVATE);
        Set<String> favCommit = sharedCommit.getStringSet("favCommitItem", new HashSet<String>());
        Iterator<String> itCommit = favCommit.iterator();
        while (itCommit.hasNext()) {
            String temp = itCommit.next();
            cmList.add(gson.fromJson(temp, CommitModel.class));
        }
        CommitFragment c = new CommitFragment();
        commitAdapter = c.new CommitAdapter(getActivity().getApplicationContext(), R.layout.commitrow, cmList);
        commitAdapter.sort(new Comparator<CommitModel>() {
            @Override
            public int compare(CommitModel l1, CommitModel l2) {
                String s1 = l1.getName(), s2 = l2.getName();
                return s1.compareTo(s2);

            }
        });
        lvCommit.setAdapter(commitAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_fav, container, false);
        TabHost tbHost = (TabHost) layout.findViewById(R.id.tabHostFav);
        tbHost.setup();

        TabHost.TabSpec spec = tbHost.newTabSpec("Tab One");
        spec.setContent(R.id.favtab1);
        spec.setIndicator("LEGISLA-TORS");
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

        lvBill = (ListView) layout.findViewById(R.id.lvbill);
        lvLeg = (ListView) layout.findViewById(R.id.lvleg);
        lvCommit = (ListView) layout.findViewById(R.id.lvcommit);
        Gson gson = new Gson();
        ArrayList<LegislatorModel> lmList = new ArrayList<>();
        SharedPreferences sharedLeg = getActivity().getSharedPreferences("Leg_Fav", MODE_PRIVATE);
        Set<String> favLeg = sharedLeg.getStringSet("favLegItem", new HashSet<String>());
        Iterator<String> itLeg = favLeg.iterator();
        while (itLeg.hasNext()) {
            String temp = itLeg.next();
            lmList.add(gson.fromJson(temp, LegislatorModel.class));
        }
        LegisFragment l = new LegisFragment();
        legislatorAdapter = l.new LegislatorAdapter(getActivity().getApplicationContext(), R.layout.legrow, lmList);
        legislatorAdapter.sort(new Comparator<LegislatorModel>() {
            @Override
            public int compare(LegislatorModel l1, LegislatorModel l2) {
                String s1 = l1.getName(), s2 = l2.getName();
                int i1 = s1.indexOf(','), i2 = s2.indexOf(',');
                return s1.substring(0, i1).compareTo(s2.substring(0, i2));

            }
        });
        lvLeg.setAdapter(legislatorAdapter);
        getLegIndex(legislatorAdapter.legList);
        LinearLayout sideLayout = (LinearLayout) layout.findViewById(R.id.favlegside);
        displayIndex(sideLayout);


        ArrayList<BillModel> bmList = new ArrayList<>();
        SharedPreferences shared = getActivity().getSharedPreferences("Bill_Fav", MODE_PRIVATE);
        Set<String> favBill = shared.getStringSet("favBillItem", new HashSet<String>());
        Iterator<String> itBill = favBill.iterator();
        while (itBill.hasNext()) {
            String temp = itBill.next();
            bmList.add(gson.fromJson(temp, BillModel.class));
        }
        BillsFragment b = new BillsFragment();
        billAdapter = b.new BillAdapter(getActivity().getApplicationContext(), R.layout.billrow, bmList);
        billAdapter.sort(new Comparator<BillModel>() {
            @Override
            public int compare(BillModel l1, BillModel l2) {
                String s1 = l1.getIntro(), s2 = l2.getIntro();
                return s2.compareTo(s1);

            }
        });
        lvBill.setAdapter(billAdapter);

        ArrayList<CommitModel> cmList = new ArrayList<>();
        SharedPreferences sharedCommit = getActivity().getSharedPreferences("Commit_Fav", MODE_PRIVATE);
        Set<String> favCommit = sharedCommit.getStringSet("favCommitItem", new HashSet<String>());
        Iterator<String> itCommit = favCommit.iterator();
        while (itCommit.hasNext()) {
            String temp = itCommit.next();
            cmList.add(gson.fromJson(temp, CommitModel.class));
        }
        CommitFragment c = new CommitFragment();
        commitAdapter = c.new CommitAdapter(getActivity().getApplicationContext(), R.layout.commitrow, cmList);
        commitAdapter.sort(new Comparator<CommitModel>() {
            @Override
            public int compare(CommitModel l1, CommitModel l2) {
                String s1 = l1.getName(), s2 = l2.getName();
                return s1.compareTo(s2);

            }
        });
        lvCommit.setAdapter(commitAdapter);


        lvLeg.setOnItemClickListener(onListClickLeg);
        lvBill.setOnItemClickListener(onListClickBill);
        lvCommit.setOnItemClickListener(onListClickCommit);
        return layout;
    }

    private AdapterView.OnItemClickListener onListClickLeg = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(getActivity(), legDetailActivity.class);
            LegislatorModel lm = (LegislatorModel) adapterView.getAdapter().getItem(i);
            intent.putExtra("leg", lm);
            startActivity(intent);
        }
    };
    private AdapterView.OnItemClickListener onListClickBill = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(getActivity(), billDetailActivity.class);
            BillModel bm = (BillModel) adapterView.getAdapter().getItem(i);
            intent.putExtra("bill", bm);
            startActivity(intent);
        }
    };
    private AdapterView.OnItemClickListener onListClickCommit = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), commitDetailActivity.class);
            CommitModel cm = (CommitModel) adapterView.getAdapter().getItem(i);
            intent.putExtra("commit", cm);
            startActivity(intent);
        }
    };

    private void getLegIndex(List<LegislatorModel> list) {
        map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            LegislatorModel temp = list.get(i);
            String index = temp.getName().substring(0, 1).toUpperCase();
            if (map.get(index) == null) {
                map.put(index, i);
            }
        }
    }

    private void displayIndex(LinearLayout sideLayout) {
        sideLayout.removeAllViews();
        TextView tv;
        Map<String, Integer> temp = map;

        List<String> sideIndex = new ArrayList<>(temp.keySet());
        for (String index : sideIndex) {
            tv = (TextView) getActivity().getLayoutInflater().inflate(R.layout.side_index_item, null);
            tv.setText(index);
            tv.setOnClickListener(this);
            sideLayout.addView(tv);
        }
    }

    @Override
    public void onClick(View view) {
        TextView selected = (TextView) view;
        lvLeg.setSelection(map.get(selected.getText()));
    }

    @Override
    public void onTabChanged(String s) {

    }
}

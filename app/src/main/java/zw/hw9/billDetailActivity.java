package zw.hw9;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

import zw.hw9.zw.hw9.models.BillModel;

public class billDetailActivity extends AppCompatActivity {
    BillModel bm;
    SharedPreferences share;
    Boolean favorite ;
    ImageButton ibfav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        setTitle("Bill Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bm = (BillModel) getIntent().getSerializableExtra("bill");
        TextView tvid, tvtitle, tvtype,tvsponsor,tvchamber,tvstatus,tvintro,tvcurl,tvvstatus,tvburl;
        tvid=(TextView)findViewById(R.id.bdeid);
        tvid.setText(bm.getId().toUpperCase());
        tvtitle=(TextView)findViewById(R.id.bdetitle);
        tvtitle.setText(bm.getTitile());
        tvtype=(TextView)findViewById(R.id.bdetype);
        tvtype.setText(bm.getType().toUpperCase());
        tvsponsor=(TextView)findViewById(R.id.bdesponsor);
        tvsponsor.setText(bm.getSponsor());
        tvchamber=(TextView)findViewById(R.id.bdechamber);
        String chamber = bm.getChamber().substring(0,1).toUpperCase()+bm.getChamber().substring(1);
        tvchamber.setText(chamber);
        tvstatus = (TextView)findViewById(R.id.bdestatus);
        tvstatus.setText(bm.getStatus());
        tvintro = (TextView)findViewById(R.id.bdeintro);
        tvintro.setText(bm.getIntro());
        tvcurl= (TextView)findViewById(R.id.bdecongress);
        tvcurl.setText(bm.getCurl());
        tvvstatus = (TextView)findViewById(R.id.bdevstatus);
        tvvstatus.setText(bm.getVstatus());
        tvburl = (TextView)findViewById(R.id.bdeburl);
        tvburl.setText(bm.getBurl());
        //
         share = getSharedPreferences("Bill_Fav",MODE_PRIVATE);
        ibfav = (ImageButton) findViewById(R.id.favBill);

        Set<String> check = share.getStringSet("favBillItem",new HashSet<String>());
        Gson gson = new Gson();
        String str = gson.toJson(bm);
        if(check.contains(str)){
            ibfav.setBackgroundResource(R.drawable.yellow);
            favorite =true;
        }else{
            favorite = false;
            ibfav.setBackgroundResource(R.drawable.fav);

        }
        ibfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!favorite){ibfav.setBackgroundResource(R.drawable.yellow);}else{  ibfav.setBackgroundResource(R.drawable.fav);}

                SharedPreferences.Editor editor = share.edit();
                Gson gson = new Gson();
                String billString = gson.toJson(bm);
                if(!favorite) {
                    MainActivity.billFav.add(billString);
                    favorite=true;
                }else{
                    MainActivity.billFav.remove(billString);
                    favorite=false;
                }
                editor.putStringSet("favBillItem", MainActivity.billFav);
                editor.commit();
            }
        });
//        if(!favorite) {
//
//            ibfav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ibfav.setBackgroundResource(R.drawable.yellow);
//                    SharedPreferences.Editor editor = share.edit();
//                    Gson gson = new Gson();
//                    String billString = gson.toJson(bm);
//                    MainActivity.billFav.add(billString);
//                    editor.putStringSet("favBillItem", MainActivity.billFav);
//                    editor.commit();
//                }
//            });
//        }else{
//            ibfav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                   ibfav.setBackgroundResource(R.drawable.fav);
//                    SharedPreferences.Editor editor = share.edit();
//                    Gson gson = new Gson();
//                    String billString = gson.toJson(bm);
//                    MainActivity.billFav.remove(billString);
//                    editor.putStringSet("favBillItem", MainActivity.billFav);
//                    editor.commit();
//                }
//            });
//        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            android.app.FragmentManager fm = getFragmentManager();
            fm.popBackStack();
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

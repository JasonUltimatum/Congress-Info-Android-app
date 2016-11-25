package zw.hw9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import zw.hw9.zw.hw9.models.BillModel;

public class billDetailActivity extends AppCompatActivity {
    BillModel bm;
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

package zw.hw9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zw.hw9.zw.hw9.models.CommitModel;

public class commitDetailActivity extends AppCompatActivity {
    CommitModel cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_detail);
        setTitle("Committee Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cm=(CommitModel) getIntent().getSerializableExtra("commit");
        TextView tvid, tvname, tvchamber, tvparent, tvcontact, tvoffice;
        ImageView ivchamber;

        tvid =(TextView)findViewById(R.id.cdeid);
        tvname=(TextView)findViewById(R.id.cdename);
        tvchamber=(TextView)findViewById(R.id.cdechamber);
        tvparent=(TextView)findViewById(R.id.cdeparent);
        tvcontact=(TextView)findViewById(R.id.cdecontact);
        tvoffice=(TextView)findViewById(R.id.cdeoffice);
        ivchamber=(ImageView)findViewById(R.id.cdeivchamber);
        tvid.setText(cm.getId());
        tvname.setText(cm.getName());
        String chamber = cm.getChamber().substring(0,1).toUpperCase()+cm.getChamber().substring(1);
        tvchamber.setText(chamber);
        tvparent.setText(cm.getParent());
        tvcontact.setText(cm.getContact());
        tvoffice.setText(cm.getOffice());
        if(chamber.equals("House")){
            Picasso.with(getApplicationContext()).load(R.drawable.h).resize(60,60).into(ivchamber);
        }else{
            Picasso.with(getApplicationContext()).load(R.drawable.s).resize(60,60).into(ivchamber);
        }
        cm = (CommitModel) getIntent().getSerializableExtra("commit");
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

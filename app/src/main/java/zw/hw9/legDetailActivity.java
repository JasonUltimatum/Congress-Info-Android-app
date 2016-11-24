package zw.hw9;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zw.hw9.zw.hw9.models.LegislatorModel;

public class legDetailActivity extends AppCompatActivity {

    LegislatorModel lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);
        setTitle("Legislator Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lm = (LegislatorModel) getIntent().getSerializableExtra("leg");
        //set party
        final Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                TextView tvparty =(TextView) findViewById(R.id.departy);
                    ImageView ivparty = (ImageView)findViewById(R.id.imgparty);
                    ImageView ivphoto = (ImageView)findViewById(R.id.dephoto);

                   Picasso.with(getApplicationContext()).load(lm.getPhoto()).resize(67, 82).into(ivphoto);
                    String party = lm.getParty();
                    if(party.equals("R")){
                        tvparty.setText("Republican");
                        Picasso.with(getApplicationContext()).load(R.drawable.r).resize(30,30).into(ivparty);
                        //ivparty.setImageResource(R.drawable.r);
                    }else if(party.equals("D")){
                        tvparty.setText("Democrat");
                        Picasso.with(getApplicationContext()).load(R.drawable.d).resize(30,30).into(ivparty);
                        //ivparty.setImageResource(R.drawable.d);
                    }else{
                        tvparty.setText("Independent");
                        Picasso.with(getApplicationContext()).load(R.drawable.i).resize(30,30).into(ivparty);
                        //ivparty.setImageResource(R.drawable.i);
                    }
                    //set name
                    TextView tvname = (TextView)findViewById(R.id.dename);
                    tvname.setText(lm.getName());

                    TextView tvemail =(TextView)findViewById(R.id.deemail);
                    tvemail.setText(lm.getEmail());
                TextView tvchamber =(TextView)findViewById(R.id.dechamber);
                String chamber = lm.getChamber().substring(0,1).toUpperCase()+lm.getChamber().substring(1);
                tvchamber.setText(chamber);
                TextView tvcontact =(TextView)findViewById(R.id.decontact);
                tvcontact.setText(lm.getContact());
                TextView tvstart =(TextView)findViewById(R.id.destart);
                tvstart.setText(lm.getStartTerm());
                TextView tvend =(TextView)findViewById(R.id.deend);
                tvend.setText(lm.getEndTerm());
                TextView tvoffcie =(TextView)findViewById(R.id.deoffice);
                tvoffcie.setText(lm.getOffice());
                TextView tvst =(TextView)findViewById(R.id.destate);
                tvst.setText(lm.getSt());
                TextView tvfax =(TextView)findViewById(R.id.defax);
                tvfax.setText(lm.getFax());
                TextView tvbirth =(TextView)findViewById(R.id.debirth);
                tvbirth.setText(lm.getBirth());

            }
        };
        Runnable r = new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        //new detailTask().execute(lm);

    }
//    class detailTask extends AsyncTask<LegislatorModel, Void, Void>{
//
//        @Override
//        protected Void doInBackground(LegislatorModel... leg) {
//            final LegislatorModel lm = leg[0];
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    TextView tvparty =(TextView) findViewById(R.id.departy);
//                    ImageView ivparty = (ImageView)findViewById(R.id.imgparty);
//                    ImageView ivphoto = (ImageView)findViewById(R.id.dephoto);
//                    //Picasso.with(getApplicationContext()).load(lm.getPhoto()).into(ivphoto);
//                    String party = lm.getParty();
//
//                    if(party.equals("R")){
//                        tvparty.setText("Republican");
//
//                        ivparty.setImageResource(R.drawable.r);
//                    }else if(party.equals("D")){
//                        tvparty.setText("Democrat");
//
//                        ivparty.setImageResource(R.drawable.d);
//                    }else{
//                        tvparty.setText("Independent");
//
//                        ivparty.setImageResource(R.drawable.i);
//                    }
//                    //set name
//                    TextView tvname = (TextView)findViewById(R.id.dename);
//                    tvname.setText(lm.getName());
//                }
//            });
//
//            return null;
//        }
//
//
//    }
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

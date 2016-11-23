package zw.hw9;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
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

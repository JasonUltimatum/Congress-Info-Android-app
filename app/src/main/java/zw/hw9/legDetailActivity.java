package zw.hw9;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import zw.hw9.zw.hw9.models.LegislatorModel;

public class legDetailActivity extends AppCompatActivity {

    LegislatorModel lm;
    SharedPreferences share;
    Boolean favorite;
    ImageButton ibfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);
        setTitle("Legislator Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lm = (LegislatorModel) getIntent().getSerializableExtra("leg");
        //set party
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                TextView tvparty = (TextView) findViewById(R.id.departy);
                ImageView ivparty = (ImageView) findViewById(R.id.imgparty);
                ImageView ivphoto = (ImageView) findViewById(R.id.dephoto);

                Picasso.with(getApplicationContext()).load(lm.getPhoto()).resize(67, 82).into(ivphoto);
                String party = lm.getParty();
                if (party.equals("R")) {
                    tvparty.setText("Republican");
                    Picasso.with(getApplicationContext()).load(R.drawable.r).resize(30, 30).into(ivparty);
                    //ivparty.setImageResource(R.drawable.r);
                } else if (party.equals("D")) {
                    tvparty.setText("Democrat");
                    Picasso.with(getApplicationContext()).load(R.drawable.d).resize(30, 30).into(ivparty);
                    //ivparty.setImageResource(R.drawable.d);
                } else {
                    tvparty.setText("Independent");
                    Picasso.with(getApplicationContext()).load(R.drawable.i).resize(30, 30).into(ivparty);
                    //ivparty.setImageResource(R.drawable.i);
                }
                //set name
                TextView tvname = (TextView) findViewById(R.id.dename);
                tvname.setText(lm.getTitle()+". "+lm.getName());

                TextView tvemail = (TextView) findViewById(R.id.deemail);
                tvemail.setText(lm.getEmail());
                TextView tvchamber = (TextView) findViewById(R.id.dechamber);
                String chamber = lm.getChamber().substring(0, 1).toUpperCase() + lm.getChamber().substring(1);
                tvchamber.setText(chamber);
                TextView tvcontact = (TextView) findViewById(R.id.decontact);

                tvcontact.setText(lm.getContact());
                TextView tvstart = (TextView) findViewById(R.id.destart);
                tvstart.setText(DateTransform.transform(lm.getStartTerm()));
                TextView tvend = (TextView) findViewById(R.id.deend);
                tvend.setText(DateTransform.transform(lm.getEndTerm()));
                ProgressBar pbterm = (ProgressBar) findViewById(R.id.determ);
                int percentNumber =calculateTerm(lm.getEndTerm(), lm.getStartTerm());
                pbterm.setProgress(percentNumber);
                TextView percent;
                percent = (TextView) findViewById(R.id.percentage);
                String percentText = percentNumber+"%";
                percent.setText(percentText);
                TextView tvoffcie = (TextView) findViewById(R.id.deoffice);
                tvoffcie.setText(lm.getOffice());
                TextView tvst = (TextView) findViewById(R.id.destate);
                tvst.setText(lm.getSt());
                TextView tvfax = (TextView) findViewById(R.id.defax);
                tvfax.setText(lm.getFax());
                TextView tvbirth = (TextView) findViewById(R.id.debirth);
                tvbirth.setText(DateTransform.transform(lm.getBirth()));
                ImageButton ibwebsite, ibtwitter, ibfacebook;
                ibwebsite = (ImageButton) findViewById(R.id.dewebsite);
                ibwebsite.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     String website = lm.getWebsite();
                                                     if (website.equals("None")) {
                                                         Toast.makeText(getApplication().getBaseContext(), "Website Not Available", Toast.LENGTH_SHORT).show();
                                                     } else {
                                                         Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                                                         startActivity(webIntent);
                                                     }
                                                 }
                                             }
                );
                ibtwitter = (ImageButton) findViewById(R.id.detwitter);
                ibtwitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String twitter = lm.getTwitter();
                        if (twitter.equals("None")) {
                            Toast.makeText(getApplication().getBaseContext(), "Twitter Not Available", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mobile.twitter.com/" + twitter));
                            startActivity(twitterIntent);
                        }
                    }
                });
                ibfacebook = (ImageButton) findViewById(R.id.defacebook);
                ibfacebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String facebook = lm.getFacebook();
                        if (facebook.equals("None")) {
                            Toast.makeText(getApplication().getBaseContext(), "Facebook Not Available", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.facebook.com/" + facebook));
                            startActivity(fbIntent);
                        }
                    }
                });
                ibfav = (ImageButton) findViewById(R.id.favLeg);
                share = getSharedPreferences("Leg_Fav", MODE_PRIVATE);
                Set<String> check = share.getStringSet("favLegItem", new HashSet<String>());
                Gson gson = new Gson();
                String str = gson.toJson(lm);
                if (check.contains(str)) {
                    ibfav.setBackgroundResource(R.drawable.yellow);
                    favorite = true;
                } else {
                    favorite = false;
                    ibfav.setBackgroundResource(R.drawable.fav);

                }
                ibfav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!favorite) {
                            ibfav.setBackgroundResource(R.drawable.yellow);
                        } else {
                            ibfav.setBackgroundResource(R.drawable.fav);
                        }
                        SharedPreferences.Editor editor = share.edit();
                        Gson gson = new Gson();
                        String billString = gson.toJson(lm);
                        if (!favorite) {

                            MainActivity.legFav.add(billString);
                            favorite = true;
                        } else {

                            MainActivity.legFav.remove(billString);
                            favorite = false;
                        }
                        editor.putStringSet("favLegItem", MainActivity.legFav);
                        editor.commit();
                    }
                });

//                if(!favorite) {
//
//                    ibfav.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ibfav.setBackgroundResource(R.drawable.yellow);
//                            SharedPreferences.Editor editor = share.edit();
//                            Gson gson = new Gson();
//                            String billString = gson.toJson(lm);
//                            MainActivity.legFav.add(billString);
//                            editor.putStringSet("favLegItem",MainActivity.legFav);
//                            editor.commit();
//                        }
//                    });
//                }else{
//                    ibfav.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            ibfav.setBackgroundResource(R.drawable.fav);
//                            SharedPreferences.Editor editor=share.edit();
//                            Gson gson = new Gson();
//                            String billString = gson.toJson(lm);
//                            MainActivity.legFav.remove(billString);
//                            editor.putStringSet("favLegItem",MainActivity.legFav);
//                            editor.commit();
//                        }
//                    });
//                }

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

    private int calculateTerm(String end, String start) {
        Timestamp s, e;
        end = end + " 00:00:00";
        start = start + " 00:00:00";
        e = Timestamp.valueOf(end);
        s = Timestamp.valueOf(start);
        Date tmp = new Date();
        return (int) ((tmp.getTime() - s.getTime()) * 100 / (e.getTime() - s.getTime()));

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

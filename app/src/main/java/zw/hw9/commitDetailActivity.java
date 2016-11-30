package zw.hw9;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;

import zw.hw9.zw.hw9.models.CommitModel;

public class commitDetailActivity extends AppCompatActivity {
    CommitModel cm;
    SharedPreferences share;
    Boolean favorite ;
    ImageButton ibfav;
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

        share = getSharedPreferences("Commit_Fav",MODE_PRIVATE);
        ibfav = (ImageButton)findViewById(R.id.favCommit);

        Set<String> check = share.getStringSet("favCommitItem",new HashSet<String>());
        Gson gson = new Gson();
        String str = gson.toJson(cm);
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
                if(!favorite) {
                    ibfav.setBackgroundResource(R.drawable.yellow);
                }else{
                    ibfav.setBackgroundResource(R.drawable.fav);
                }
                SharedPreferences.Editor editor=share.edit();
                Gson gson = new Gson();
                String billString = gson.toJson(cm);
                if(!favorite) {
                    MainActivity.commitFav.add(billString);
                    favorite=true;
                }else{
                    MainActivity.commitFav.remove(billString);
                    favorite=false;
                }
                editor.putStringSet("favCommitItem",MainActivity.commitFav);
                editor.commit();
            }
        });
//        if(!favorite) {
//
//            ibfav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ibfav.setBackgroundResource(R.drawable.yellow);
//                    SharedPreferences.Editor editor=share.edit();
//                    Gson gson = new Gson();
//                    String billString = gson.toJson(cm);
//                    MainActivity.commitFav.add(billString);
//                    editor.putStringSet("favCommitItem",MainActivity.commitFav);
//                    editor.commit();
//                }
//            });
//        }else{
//            ibfav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ibfav.setBackgroundResource(R.drawable.fav);
//                    SharedPreferences.Editor editor=share.edit();
//                    Gson gson = new Gson();
//                    String billString = gson.toJson(cm);
//                    MainActivity.commitFav.remove(billString);
//                    editor.putStringSet("favCommitItem",MainActivity.commitFav);
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

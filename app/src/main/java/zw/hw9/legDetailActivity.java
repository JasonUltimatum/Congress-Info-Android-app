package zw.hw9;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zw.hw9.zw.hw9.models.LegislatorModel;

public class legDetailActivity extends AppCompatActivity {
    String test = null;
    private TextView tv =null;
    LegislatorModel lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        test = getIntent().getStringExtra("Test");
        tv =(TextView)findViewById(R.id.detailtest);
        lm = (LegislatorModel) getIntent().getSerializableExtra("leg");
        tv.setText("Name: "+lm.getName());


//        button= (Button)findViewById(R.id.back);
//        onClickButtonListener(button);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            android.app.FragmentManager fm= getFragmentManager();
               fm.popBackStack();
                finish();
                return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                android.app.FragmentManager fm= getFragmentManager();
//                fm.popBackStack();
//                finish();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
    }

    //    private void onClickButtonListener(Button button){
//
//        button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//               finish();
//
//            }
//        });
//    }
}

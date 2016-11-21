package zw.hw9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zw.hw9.zw.hw9.models.LegislatorModel;

public class legDetailActivity extends AppCompatActivity {
    String test = null;
    private TextView tv =null;
    LegislatorModel lm;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);
        test = getIntent().getStringExtra("Test");
        tv =(TextView)findViewById(R.id.detailtest);


        lm = (LegislatorModel) getIntent().getSerializableExtra("leg");
        tv.setText("Name: "+lm.getName());
        button= (Button)findViewById(R.id.back);
        onClickButtonListener(button);
    }
    private void onClickButtonListener(Button button){

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               finish();

            }
        });
    }
}

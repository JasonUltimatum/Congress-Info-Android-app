package zw.hw9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class legDetailActivity extends AppCompatActivity {
    String test = null;
    private TextView tv =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);
        test = getIntent().getStringExtra("Test");
        tv =(TextView)findViewById(R.id.detailtest);
        tv.setText("Test :"+test);
    }
}

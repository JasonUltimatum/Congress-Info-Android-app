package zw.hw9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

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

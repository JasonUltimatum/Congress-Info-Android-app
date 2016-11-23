package zw.hw9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import zw.hw9.zw.hw9.models.CommitModel;

public class commitDetailActivity extends AppCompatActivity {
    CommitModel cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_detail);
        setTitle("Committee Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

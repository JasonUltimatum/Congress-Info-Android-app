package zw.hw9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Set<String> legFav;
    public static Set<String> billFav;
    public static Set<String> commitFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedLeg = getSharedPreferences("Leg_Fav",MODE_PRIVATE);
        legFav = sharedLeg.getStringSet("favLegItem",new HashSet<String>());

        SharedPreferences sharedBill = getSharedPreferences("Bill_Fav",MODE_PRIVATE);
        billFav =sharedBill.getStringSet("favBillItem",new HashSet<String>());

        SharedPreferences sharedCommit = getSharedPreferences("Commit_Fav",MODE_PRIVATE);
        commitFav = sharedCommit.getStringSet("favCommitItem",new HashSet<String>());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LegisFragment legisFragment = new LegisFragment();
        FragmentManager legisManager = getSupportFragmentManager();
        legisManager.beginTransaction().replace(R.id.content_main, legisFragment, legisFragment.getTag()).commit();
        setTitle("Legislators");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            LegisFragment legisFragment = new LegisFragment();
            FragmentManager legisManager = getSupportFragmentManager();
            legisManager.beginTransaction().replace(R.id.content_main, legisFragment, legisFragment.getTag()).commit();
            setTitle("Legislators");
        } else if (id == R.id.nav_gallery) {
            BillsFragment billsFragment = new BillsFragment();
            FragmentManager billsManager =getSupportFragmentManager();
            billsManager.beginTransaction().replace(R.id.content_main,billsFragment,billsFragment.getTag()).commit();
            setTitle("Bills");
        } else if (id == R.id.nav_slideshow) {
            CommitFragment commitFragment = new CommitFragment();
            FragmentManager commitManager = getSupportFragmentManager();
            commitManager.beginTransaction().replace(R.id.content_main,commitFragment,commitFragment.getTag()).commit();
            setTitle("Committees");
        } else if (id == R.id.nav_manage) {
           FavFragment favFragment = new FavFragment();
            FragmentManager favManager = getSupportFragmentManager();
            favManager. beginTransaction().replace(R.id.content_main,favFragment,favFragment.getTag()).commit();
            setTitle("Favorites");
        } else if (id == R.id.nav_share) {

            Intent intent = new Intent(MainActivity.this,aboutme.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

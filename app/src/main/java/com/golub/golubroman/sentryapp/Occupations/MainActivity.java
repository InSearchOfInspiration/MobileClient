package com.golub.golubroman.sentry;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;


import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.golub.golubroman.sentry.AddOccupation.AddOccupationFragment;
import com.golub.golubroman.sentry.Characteristics.CharacteristicsFragment;
import com.golub.golubroman.sentry.LocationResearch.LocationFragment;
import com.golub.golubroman.sentry.Occupations.OccupationsAdapter;
import com.golub.golubroman.sentry.Occupations.OccupationsFragment;
import com.golub.golubroman.sentry.Statistics.StatisticsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OccupationsFragment.OnAddNewOccupationListener,
        OccupationsFragment.OnChosedOccupationListener,
        LocationFragment.LocationFragmentListener{

    private Fragment addOccupationFragment;
    private Fragment characteristicsFragment;
    private Fragment occupationsFragment;
    private Fragment statisticsFragment;
    private Fragment locationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addOccupationFragment = AddOccupationFragment.newInstance();
        characteristicsFragment = CharacteristicsFragment.newInstance();
        occupationsFragment = OccupationsFragment.newInstance();
        statisticsFragment = StatisticsFragment.newInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //replaceFragment(occupationsFragment, false);
        locationFragment = LocationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.drawer_layout, locationFragment);
        fragmentTransaction.commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_occupations) {
            replaceFragment(occupationsFragment, false);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_statistics) {
            replaceFragment(statisticsFragment, false);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_add_occupation) {
            replaceFragment(addOccupationFragment, false);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        return true;
    }

    private void replaceFragment(Fragment fragment, boolean add){
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.
                replace(R.id.content_main, fragment, null);
        if(add)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddNewOccupation() {
        replaceFragment(addOccupationFragment, true);
    }

    @Override
    public void onChosedOccupation(OccupationsAdapter.OccupationsViewHolder vh, OccupationsAdapter a) {
        replaceFragment(characteristicsFragment, true);
    }

    @Override
    public void getPlace(double longitude, double latitude) {

    }
}

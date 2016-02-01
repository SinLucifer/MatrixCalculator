package org.sin.matrixcalculator;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.sin.matrixcalculator.item.AdjugateFragment;
import org.sin.matrixcalculator.item.DeterminFragment;
import org.sin.matrixcalculator.item.DivisionFragment;
import org.sin.matrixcalculator.item.InverseFragment;
import org.sin.matrixcalculator.item.MultipleFragment;
import org.sin.matrixcalculator.item.PlusFragment;
import org.sin.matrixcalculator.item.SubtractionFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;

    private PlusFragment mPlus;
    private SubtractionFragment mSub;
    private MultipleFragment mMult;
    private DivisionFragment mDivi;
    private InverseFragment mInverse;
    private DeterminFragment mDetermin;
    private AdjugateFragment mAdjugate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.plus) {
            mPlus = new PlusFragment();
            fragmentTransaction.replace(R.id.frame_content,mPlus);
        } else if (id == R.id.subtraction) {
            mSub = new SubtractionFragment();
            fragmentTransaction.replace(R.id.frame_content,mSub);
        } else if (id == R.id.multiple) {
            mMult = new MultipleFragment();
            fragmentTransaction.replace(R.id.frame_content,mMult);
        } else if (id == R.id.division) {
            mDivi = new DivisionFragment();
            fragmentTransaction.replace(R.id.frame_content,mDivi);
        } else if (id == R.id.determinant) {
            mDetermin = new DeterminFragment();
            fragmentTransaction.replace(R.id.frame_content,mDetermin);
        } else if (id == R.id.adjugate) {
            mAdjugate = new AdjugateFragment();
            fragmentTransaction.replace(R.id.frame_content,mAdjugate);
        } else if (id == R.id.inverse) {
            mInverse = new InverseFragment();
            fragmentTransaction.replace(R.id.frame_content,mInverse);
        }

        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

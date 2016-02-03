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

import org.sin.matrixcalculator.Setting_Fragment.SetXY_Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;

    public static final String ARGUMENT = "calculate_method";
    public static final String PLUS = "Plus";
    public static final String SUBTRACT = "Subtract";
    public static final String MULTIPLE = "Multiple";
    public static final String DIVIDE = "Divide";
    public static final String DETERMINANT = "Determinant";
    public static final String ADJUGATE = "Adjugate Matrix";
    public static final String INVERSE = "Inverse Matrix";

    private SetXY_Fragment mSetXY;


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
        bundle = new Bundle();


        mSetXY = new SetXY_Fragment();

        if (id == R.id.plus) {
            bundle.putString(ARGUMENT,PLUS);
        } else if (id == R.id.subtraction) {
            bundle.putString(ARGUMENT,SUBTRACT);
        } else if (id == R.id.multiple) {
            bundle.putString(ARGUMENT,MULTIPLE);
        } else if (id == R.id.division) {
            bundle.putString(ARGUMENT,DIVIDE);
        } else if (id == R.id.determinant) {
            bundle.putString(ARGUMENT,DETERMINANT);
        } else if (id == R.id.adjugate) {
            bundle.putString(ARGUMENT,ADJUGATE);
        } else if (id == R.id.inverse) {
            bundle.putString(ARGUMENT,INVERSE);
        }

        mSetXY.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_content,mSetXY); //need FrameLayout ID,not layout
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

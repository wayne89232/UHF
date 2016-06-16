package com.example.wayne89232.uhf;

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
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.support.design.widget.CoordinatorLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import rfid.ivrjacku1.IvrJackAdapter;

import rfid.ivrjacku1.IvrJackService;
import rfid.ivrjacku1.IvrJackStatus;

public abstract class MainActivity extends AppCompatActivity
        implements IvrJackAdapter, NavigationView.OnNavigationItemSelectedListener {

    public static IvrJackService reader;
    private boolean bOpened;
    static {
        reader = null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.bOpened = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new btnQuery_Click());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        reader = new IvrJackService();
        reader.open(this, this);
    }
    public void onDestroy() {
        if (reader != null) {
            reader.close();
            //Log.i("HEX", "reader close");
        }
        super.onDestroy();
    }
    class C00091 implements OnClickListener {
        C00091() {
        }

        public void onClick(View paramView) {
            if (MainActivity.this.bOpened) {
                //MainActivity.this.showToast("Please stop the inventory tag action.");
                return;
            }
            //Intent intent1 = new Intent();
            //intent1.setClass(Demo.this, activity_Setting.class);
            //MainActivity.this.startActivity(intent1);
        }
    }
    private class btnQuery_Click implements OnClickListener {

        class C00111 extends Thread {
            C00111() {
            }

            public void run() {
                try {
                    //MainActivity.this.cMsg = "Device communication error, make sure that is plugged.";
                    //MainActivity.this.bSuccess = false;
                    int ret = MainActivity.reader.readEPC(!MainActivity.this.bOpened);
                    System.out.println(ret);
                    Toast.makeText(MainActivity.this    , ret, Toast.LENGTH_LONG).show();

                    /*if (ret != 0 || MainActivity.this.bCancel) {
                        if (ret == -1) {
                            MainActivity.this.cMsg = "Device is running low battery, please charge!";
                        }
                        MainActivity.this.handler.sendEmptyMessage(1);
                    }
                    MainActivity.this.bSuccess = true;
                    MainActivity.this.handler.sendEmptyMessage(1);*/
                } catch (Exception e) {
                    //MainActivity.this.cMsg = "Unknown error.";
                    //MainActivity.this.bSuccess = false;
                }
            }
        }
        public void onClick(View v) {
            //MainActivity.this.btnQuery.setEnabled(false);
            if (MainActivity.this.bOpened) {
                //MainActivity.this.pd = ProgressDialogEx.show(Demo.this, "Stop read epc");
            } else {
                //Demo.this.pd = ProgressDialogEx.show(Demo.this, "Start read epc");
            }
            new C00111().start();
        }
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

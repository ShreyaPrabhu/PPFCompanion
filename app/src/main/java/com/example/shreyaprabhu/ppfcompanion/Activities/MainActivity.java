package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shreyaprabhu.ppfcompanion.R;
import com.example.shreyaprabhu.ppfcompanion.TabFragments.ReportFragment;
import com.example.shreyaprabhu.ppfcompanion.DataValidationUtils.DateUtils;
import com.example.shreyaprabhu.ppfcompanion.DataValidationUtils.ValidateEntry;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.ParseException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemSelectedListener {

    EditText amount_deposited;
    EditText date;
    Button calculate;
    Button refresh;
    String ppfmode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //ca-app-pub-3940256099942544~3347511713

        /** Spinner Adapter Initialisation
         * Create an ArrayAdapter using the string array and a default spinner layout
         * Specify the layout to use when the list of choices appears
         * Apply the adapter to the spinner
         * Then you need to specify the interface implementation by calling setOnItemSelectedListener()
         */
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ppf_mode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        date = (EditText) findViewById(R.id.date);
        amount_deposited = (EditText) findViewById(R.id.amount);
        calculate = (Button) findViewById(R.id.calculate);
        refresh = (Button) findViewById(R.id.refresh);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        date.setText(year+"");

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PPFReport.class);
                String amount = amount_deposited.getText().toString();
                String startyear = date.getText().toString();

                try {
                    if(ValidateEntry.validate(MainActivity.this,amount,startyear)){
                            i.putExtra("amountmessage", amount);
                            i.putExtra("startdatemessage",startyear);
                            i.putExtra("ppfmodemessage",ppfmode);
                            startActivity(i);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recreate();
                amount_deposited.setText("");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                date.setText(year+"");

            }
        });
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

        if (id == R.id.nav_myPlans) {
            Intent intent =new Intent(this,MyPlan.class);
            startActivity(intent);

        } else if (id == R.id.nav_calculator) {

        } else if (id == R.id.nav_details) {

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Toast.makeText(this,"Selected mode is " + adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
        ppfmode = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Warn when nothing selected
    }
}

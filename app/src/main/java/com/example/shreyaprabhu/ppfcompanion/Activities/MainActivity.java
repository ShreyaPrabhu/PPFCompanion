package com.example.shreyaprabhu.ppfcompanion.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.IntentCompat;
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
import com.example.shreyaprabhu.ppfcompanion.Utils.DateUtils;
import com.example.shreyaprabhu.ppfcompanion.Utils.ValidateEntry;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemSelectedListener {

    EditText amount_deposited;
    EditText date;
    EditText no_of_years;
    Button calculate;

    static final int DATE_PICKER_ID = 1111;
    private int year;
    private int month;
    private int day;
    StringBuilder date_string;

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

        /** Spinner Adapter Initialisation
         * Create an ArrayAdapter using the string array and a default spinner layout
         * Specify the layout to use when the list of choices appears
         * Apply the adapter to the spinner
         * Then you need to specify the interface implementation by calling setOnItemSelectedListener()
         */
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ppf_mode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        date = (EditText) findViewById(R.id.date);
        amount_deposited = (EditText) findViewById(R.id.amount);
        no_of_years = (EditText) findViewById(R.id.year);
        calculate = (Button) findViewById(R.id.calculate);

        //Date Picker
        date.setText(Calendar.getInstance().toString());
        Calendar calendar = Calendar.getInstance();

        //SHOW DATE
        day= calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        date_string = DateUtils.showDate(day,month,year);
        date.setText(date_string);

        //DATE DIALOG BOX
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PPFReport.class);

                String no_of_year = no_of_years.getText().toString();
                String amount = amount_deposited.getText().toString();


                try {
                    if(ValidateEntry.validate(amount,no_of_year,day,month,year,date_string.toString())){
                        i.putExtra("amountmessage", amount);
                        i.putExtra("noOfyearmessage",no_of_year);
                        i.putExtra("startdatemessage",date_string.toString());
                        i.putExtra("ppfmodemessage", ppfmode);
                        Toast.makeText(MainActivity.this,"validated" + amount + no_of_year + date_string.toString() + ppfmode,Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            date_string = DateUtils.showDate(day,month,year);
            date.setText(date_string);
        }
    };



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

package com.zaikatalash.dashboard;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class TodaySpecial extends Activity implements OnItemSelectedListener {
        Spinner spinnerCuisine, spinnerCategory;
    private TextView Output;
    private Button changeDate;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_today_special);
            spinnerCuisine = (Spinner) findViewById(R.id.spinnerCuisine);

            spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);

            spinnerCuisine.setOnItemSelectedListener(this);

        Output = (TextView) findViewById(R.id.textView3);
        changeDate = (Button) findViewById(R.id.button1);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        Output.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));


        // Button listener to show date picker dialog

        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);

            }

        });
        }



        @Override

        public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
                                   long arg3) {

            parent.getItemAtPosition(pos);
            if (pos == 0) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter
                        .createFromResource(this, R.array.cuisine_select,
                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            } else if (pos == 1) {

                ArrayAdapter<CharSequence> adapter = ArrayAdapter

                        .createFromResource(this, R.array.cuisine_indian,

                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            } else if (pos == 2) {

                ArrayAdapter<CharSequence> adapter = ArrayAdapter

                        .createFromResource(this, R.array.cuisine_world,

                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            } else if (pos == 3) {

                ArrayAdapter<CharSequence> adapter = ArrayAdapter

                        .createFromResource(this, R.array.cuisine_desserts,

                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            } else if (pos == 4) {

                ArrayAdapter<CharSequence> adapter = ArrayAdapter

                        .createFromResource(this, R.array.cuisine_snacks,

                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            } else if (pos == 5) {

                ArrayAdapter<CharSequence> adapter = ArrayAdapter

                        .createFromResource(this, R.array.cuisine_salads,

                                android.R.layout.simple_spinner_item);

                spinnerCategory.setAdapter(adapter);

            }

        }

        @Override

        public void onNothingSelected(AdapterView<?> arg0) {


        }

        @Override
        protected Dialog onCreateDialog(int id) {
            switch (id) {
                case DATE_PICKER_ID:

                    // open datepicker dialog.
                    // set date picker for current date
                    // add pickerListener listner to date picker
                    return new DatePickerDialog(this, pickerListener, year, month,day);
            }
            return null;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }


        private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

            // when dialog box is closed, below method will be called.
            @Override
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {

                year  = selectedYear;
                month = selectedMonth;
                day   = selectedDay;

                // Show selected date
                Output.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));

            }
        };
    }

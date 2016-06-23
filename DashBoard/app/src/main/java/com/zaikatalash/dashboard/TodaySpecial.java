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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class TodaySpecial extends Activity implements OnItemSelectedListener {
        Spinner spinnerCuisine, spinnerCategory;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_today_special);
            spinnerCuisine = (Spinner) findViewById(R.id.spinnerCuisine);

            spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);

            spinnerCuisine.setOnItemSelectedListener(this);
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

        DatePicker datePicker;
        Calendar calendar;
        TextView dateView;
        int year, month, day;

            dateView = (TextView) findViewById(R.id.textView3);
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            showDate(year, month+1, day);
        }

        @SuppressWarnings("deprecation")
        public void setDate(View view) {
            showDialog(999);
            Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        protected Dialog onCreateDialog(int id) {
            int year=0,month=0,day=0;
            // TODO Auto-generated method stub
            if (id == 999) {
                return new DatePickerDialog(this, myDateListener, year, month, day);
            }
            return null;
        }

        private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                // arg1 = year
                // arg2 = month
                // arg3 = day
                showDate(arg1, arg2+1, arg3);
            }
        };

        private void showDate(int year, int month, int day) {

            TextView dateView = null;
           dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
    }

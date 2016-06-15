package acadgild.com.spinnerdemoexample;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.ArrayAdapter;

import android.widget.Spinner;



public class MainActivity extends Activity implements OnItemSelectedListener {



    Spinner spinnerCountry, spinnerCity;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);

        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        spinnerCountry.setOnItemSelectedListener(this);

    }



    @Override

    public void onItemSelected(AdapterView<?> parent, View arg1, int pos,

                               long arg3) {

        parent.getItemAtPosition(pos);

        if (pos == 0) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_india,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        } else if (pos == 1) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_pakisthan,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        } else if (pos == 2) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_srilanka,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        }

    }



    @Override

    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
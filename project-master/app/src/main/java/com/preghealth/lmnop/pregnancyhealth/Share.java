package com.preghealth.lmnop.pregnancyhealth;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Share extends AppCompatActivity  {

    public static EditText etNormalText;
    public static EditText etEmailAddrss;
    String name, email;
    private Button btnSubmit;
    ListView lview;
    static String[] To;
    String nameclick,emailclick;
    ImageView refresh;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerViews();
        dbHelper = new DBHelper(this);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation())
                    submitForm();
               // else Toast.makeText(Share.this, "Form contains error", Toast.LENGTH_LONG).show();

            }
        });

        lview = (ListView) findViewById(R.id.contactsview);
        lview.setAdapter(new ContactsAdapter(getApplicationContext(), R.layout.contactstemplate, dbHelper.getnames()));
        lviewlistener();

        refresh = (ImageView) findViewById(R.id.btn_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lview.setAdapter(new ContactsAdapter(getApplicationContext(), R.layout.contactstemplate, dbHelper.getnames()));
                lviewlistener();
            }
        });

    }



    private void submitForm() {
        Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();

        name =etNormalText.getText().toString();
        email=etEmailAddrss.getText().toString();

        dbHelper.addName(name, email);
        lview.setAdapter(new ContactsAdapter(getApplicationContext(), R.layout.contactstemplate, dbHelper.getnames()));
        lviewlistener();

        To= new String[dbHelper.getnames().size()];
        for(int i=0;i<dbHelper.getnames().size();i++){
          To[i] =dbHelper.getnames().get(i).getEmail();
        }


    }


    private void lviewlistener() {
        lview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nameclick =dbHelper.getnames().get(position).getName();
                emailclick=dbHelper.getnames().get(position).getEmail();

                AlertDialog.Builder builder = new AlertDialog.Builder(Share.this);
                builder.setTitle("Are you sure you want to delete contact?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int j = dbHelper.deleterecord(nameclick, emailclick);
                        lview.setAdapter(new ContactsAdapter(getApplicationContext(), R.layout.contactstemplate, dbHelper.getnames()));
                        lviewlistener();

                        Toast.makeText(getApplicationContext(), "Contact deleted", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });




    }


    private void registerViews() {
        etNormalText = (EditText) findViewById(R.id.et_normal_text);
        etNormalText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etNormalText);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etEmailAddrss = (EditText) findViewById(R.id.et_email_address);
        etEmailAddrss.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //Validation.isEmailAddress(etEmailAddrss, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }


    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(etNormalText)) ret = false;
        if (!Validation.isEmailAddress(etEmailAddrss, true)) ret = false;
        return ret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sharescores) {
            Intent i1 = new Intent(Share.this, Share.class);
            startActivity(i1);
            return true;
        }

        if (id == R.id.chartprogress) {
            Intent i2 = new Intent(Share.this, Chart.class);
            startActivity(i2);
            return true;
        }

        if (id == R.id.home) {
            Intent i3 = new Intent(Share.this, MainActivity.class);
            startActivity(i3);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
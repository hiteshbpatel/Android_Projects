package com.preghealth.lmnop.pregnancyhealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Parameter> mparam;
    ListView listView;
    CustomAdapter customAdapter;
    int i, decimalscore;
    static int SWEEP;
    static double[] num, den, div;
    TextView tvtext,tvsweep;
    String message, currentdate;
    SharedPreferences sharedPreferences;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        num=new double[5];
        den=new double[5];
        div=new double[5];
        mparam = new ArrayList<Parameter>();
        setparameters();

        customAdapter= new CustomAdapter(getApplicationContext(),R.layout.listviewformat, MainActivity.mparam);
        listView = (ListView)findViewById(R.id.lview);
        listView.setAdapter(customAdapter);
        dbHelper = new DBHelper(this);

        tvtext=(TextView)findViewById(R.id.tv_sweeptext);
        tvsweep=(TextView)findViewById(R.id.tv_sweepscore);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double score=0;
               for (int i = 0; i < mparam.size(); i++) {
                    num[i] = (mparam.get(i).getActual()) ;
                    den[i]=(mparam.get(i).getTarget());
                   div[i]=num[i]/den[i];
                    score = score + div[i];
                }
                SWEEP=(int)Math.round(score*20);

                tvtext.setText("Your SWEEP Score for today is :");
                tvsweep.setText(String.valueOf(SWEEP));

                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
                currentdate = sdf.format(new Date());

                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(currentdate, String.valueOf(SWEEP));
                editor.commit();


                decimalscore=(int)Math.floor(SWEEP/10);

                switch (decimalscore){

                    case 10:message="You are a rockstar mom!! ";
                        break;

                    case 9: message="Proud of you mommy! You are awesome";
                        break;

                    case 8: message="Well done momma";
                        break;

                    case 7: message="Try for something better tomorrow?";
                        break;

                    case 6:message="You forgot about me today mommy! ";
                        break;

                    case 5:message="Need your help to grow Mom. HELP ME!";
                        break;

                    default:message="Please give me priority Mom. I NEED YOU!!";
                        break;
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                String[] To= new String[dbHelper.getnames().size()];
                for(int i=0;i<dbHelper.getnames().size();i++){
                    To[i] =dbHelper.getnames().get(i).getEmail();
                }

               if(Share.To!=null)sendmail();
            }
        });


    }

    private void setparameters() {
        mparam.add(new Parameter(1, "Sleep","hours",8,24,R.drawable.ic_sleep,8));
        mparam.add(new Parameter(2, "Water","glasses",8,16,R.drawable.ic_water,8));
        mparam.add(new Parameter(3,"Eat","meals",5,10,R.drawable.ic_eats,5));
        mparam.add(new Parameter(4, "Exercise","mins",30,120,R.drawable.ic_exercise,30));
        mparam.add(new Parameter(5, "Pills", "times", 3, 6, R.drawable.ic_pills,3));

    }

    private void sendmail() {

        String msg = "Today's SWEEP Score is " + MainActivity.SWEEP + "\n"
                + "Sleep: " + (int)MainActivity.num[0] + " hours" + "\n"
                + "Water: " + (int)MainActivity.num[1] + " glasses" + "\n"
                + "Eat: " + (int)MainActivity.num[2] + " meals" + "\n"
                + "Exercise: " + (int)MainActivity.num[3] + " mins" + "\n"
                + "Pills: " + (int) MainActivity.num[4] + " times" + "\n";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, Share.To);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Today's SWEEP Score");
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id== R.id.sharescores){
            Intent i1=new Intent(MainActivity.this,Share.class);
            startActivity(i1);
            finish();
            return true;
        }

        if (id== R.id.chartprogress){
            Intent i2=new Intent(MainActivity.this,Chart.class);
            startActivity(i2);
            return true;
        }

        if(id==R.id.home){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

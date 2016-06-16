package com.preghealth.lmnop.pregnancyhealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Chart extends AppCompatActivity {

    ArrayList<Dayscore> mlist;
    ArrayList<BarEntry> entries;
    ArrayList<String> label;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> values = preferences.getAll();
        List<Dayscore> mlist = new ArrayList<>();
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            Dayscore d = new Dayscore();
            d.date = entry.getKey();
            d.score = Integer.parseInt(entry.getValue().toString());
            mlist.add(d);}

        Dayscore dummy = new Dayscore("",0);
        for (int i=0; i<5; i++)mlist.add(dummy);

        entries = new ArrayList<BarEntry>();
        label= new ArrayList<String>();

        for(int i=0;i<mlist.size();i++){
        String date =   mlist.get(i).getDate();
        label.add(date);
        int sweep = mlist.get(i).getScore();
        entries.add(new BarEntry((float)sweep,i));
        }

        BarDataSet dataset= new BarDataSet(entries,"Daily Score");
        BarChart chart = (BarChart)findViewById(R.id.chart);
        BarData data = new BarData(label, dataset);
        chart.setDescription("Your scores");
        chart.setDescriptionPosition(0, 0);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);



        chart.getAxisLeft().setDrawGridLines(true);
        chart.getAxisLeft().setAxisMaxValue(100f);
        chart.getAxisLeft().setAxisMinValue(0f);
        chart.getAxisRight().setEnabled(false);

        dataset.setColor(-3355444);
        dataset.setValueTextSize(0f);
        chart.setData(data);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            Intent i1=new Intent(this,Share.class);
            startActivity(i1);
            return true;
        }

        if (id== R.id.chartprogress){
            Intent i2=new Intent(this,Chart.class);
            startActivity(i2);
            return true;
        }

        if(id==R.id.home){
            Intent i4=new Intent(this,MainActivity.class);
            startActivity(i4);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}

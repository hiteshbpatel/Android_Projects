package me.rk.andses4ass1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by airodyra on 6/19/2016.
 */
public class ListViewExample extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        listView= (ListView) findViewById(R.id.listView1);

        final String[] data={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        ArrayAdapter stringArrayAdapter=new ArrayAdapter<String>(this, R.layout.simple_listview, R.id.textView,data);
        listView.setAdapter(stringArrayAdapter);

    }
}

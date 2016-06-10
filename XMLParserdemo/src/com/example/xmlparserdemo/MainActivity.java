package com.example.xmlparserdemo;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	static final String URL = "http://api.androidhive.info/pizza/?format=xml";
	static final String KEY_ITEM = "item";
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<HashMap<String, String>> ar = new ArrayList<HashMap<String, String>>();
		XMLParser xmlpars = new XMLParser();

		String xml = xmlpars.getXMLfromURL(URL);
		Document doc = xmlpars.getDOMElement(xml);

		NodeList nod = doc.getElementsByTagName(KEY_ITEM);

		for (int i = 0; i < nod.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nod.item(i);
			map.put(KEY_ID, xmlpars.getValue(e, KEY_ID));
			map.put(KEY_NAME, xmlpars.getValue(e, KEY_NAME));
			map.put(KEY_COST, "Rs." + xmlpars.getValue(e, KEY_COST));
			map.put(KEY_DESC, xmlpars.getValue(e, KEY_DESC));

			ar.add(map);
		}

		ListAdapter adapter = new SimpleAdapter(this, ar, R.layout.list_item,
				new String[] { KEY_NAME, KEY_DESC, KEY_COST }, new int[] {
						R.id.name, R.id.description, R.id.cost });
		
		setListAdapter(adapter);
		
		ListView lv=getListView();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name=((TextView)view.findViewById(R.id.name)).getText().toString();
				String cost=((TextView)view.findViewById(R.id.cost)).getText().toString();
				String description=((TextView)view.findViewById(R.id.description)).getText().toString();
				
				Intent i=new Intent(MainActivity.this, SingleMainActivity.class);
				
				i.putExtra(KEY_NAME, name);
				i.putExtra(KEY_COST, cost);
				i.putExtra(KEY_DESC, description);
				startActivity(i);
			}
		});

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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

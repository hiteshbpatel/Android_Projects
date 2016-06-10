package com.example.xmlparserdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SingleMainActivity extends ActionBarActivity {

	static final String KEY_NAME="name";
	static final String KEY_COST="cost";
	static final String KEY_DESC="description";
	TextView name,cost,desc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_main);
		name=(TextView)findViewById(R.id.textView1);
		cost=(TextView)findViewById(R.id.textView2);
		desc=(TextView)findViewById(R.id.textView3);
		Intent i=getIntent();
		name.setText(i.getStringExtra(KEY_NAME));
		cost.setText(i.getStringExtra(KEY_COST));
		desc.setText(i.getStringExtra(KEY_DESC));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_main, menu);
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

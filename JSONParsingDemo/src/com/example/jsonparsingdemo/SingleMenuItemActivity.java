package com.example.jsonparsingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity {

	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "hoddo";
	private static final String TAG_PHONE_MOBILE = "contact";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_menu_item);
		
		Intent intent = getIntent();
		
		    String name = intent.getStringExtra(TAG_NAME);
	        String cost = intent.getStringExtra(TAG_EMAIL);
	        String description = intent.getStringExtra(TAG_PHONE_MOBILE);
	        
	        // Displaying all values on the screen
	        TextView lblName = (TextView) findViewById(R.id.email_label);
	        TextView lblCost = (TextView) findViewById(R.id.name_label);
	        TextView lblDesc = (TextView) findViewById(R.id.mobile_label);
	        
	        lblName.setText(cost);
	        lblCost.setText(name);
	        lblDesc.setText(description);
	}
}

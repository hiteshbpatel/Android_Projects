package com.example.binderservicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binderservicedemo.Binderdemo.LocalBinder;

public class MainActivity extends Activity {

	boolean mbounded;
	Binderdemo mbinder;
	TextView text;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text=(TextView)findViewById(R.id.textView1);
		btn=(Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text.setText(mbinder.getTime());
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Intent mIntent=new Intent(this, Binderdemo.class);
		bindService(mIntent, mConnection, BIND_AUTO_CREATE);
	}
	
	ServiceConnection mConnection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "Service is disconnected", 1000).show();
			mbounded=false;
			mbinder=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "Service is Connected", 1000).show();
			mbounded=true;
			LocalBinder mLocalbinder=(LocalBinder) service;
			mbinder=mLocalbinder.getBinderInstance();
		}
	};
	
	protected void onStop() {
		super.onStop();
		if(mbounded){
			unbindService(mConnection);
			mbounded=false;
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

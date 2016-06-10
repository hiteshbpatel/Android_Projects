package com.example.threademo;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView txt=(TextView)findViewById(R.id.textView1);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while (i<100) {
					SystemClock.sleep(250);
					i++;
					final int curCount=i;
				//	if(curCount%1==0){
						txt.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								txt.setText(curCount +"% Complete");
							}
						});
				//	}
				}
				txt.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						txt.setText("Count Complete");
					}
				});
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

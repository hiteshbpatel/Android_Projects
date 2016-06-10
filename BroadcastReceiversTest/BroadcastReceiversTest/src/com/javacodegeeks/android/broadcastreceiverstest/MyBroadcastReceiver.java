package com.javacodegeeks.android.broadcastreceiverstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		// Extract data included in the Intent
		CharSequence intentData = intent.getCharSequenceExtra("message");	
		Toast.makeText(context, "Javacodegeeks received the Intent's message: "+intentData, Toast.LENGTH_LONG).show();
	}

}

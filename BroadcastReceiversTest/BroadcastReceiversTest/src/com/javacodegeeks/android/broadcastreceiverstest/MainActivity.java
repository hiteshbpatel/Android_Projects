package com.javacodegeeks.android.broadcastreceiverstest;

import com.javacodegeeks.android.broadcastreceiverstest.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void broadcastCustomIntent(View view)
    {
       Intent intent = new Intent("MyCustomIntent");
    
       EditText et = (EditText)findViewById(R.id.extraIntent);
       // add data to the Intent
       intent.putExtra("message", (CharSequence)et.getText().toString());
       intent.setAction("com.javacodegeeks.android.A_CUSTOM_INTENT");
       sendBroadcast(intent);
    }
    
}

package com.example.audioplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView mainList;
	MediaPlayer mp;
	final String[] listContent = {"Moh Moh Ke Dhaage", "Moh Moh Ke Dhaage"};
	final int[] resID = {R.raw.music, R.raw.music};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initializing variables
		mainList = (ListView) findViewById(R.id.listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContent);
		mainList.setAdapter(adapter);

		mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				playSong(i);

			}

		});

	}

	public void playSong(int songIndex) {
		// Play song
		mp.reset();// stops any current playing song
		mp = MediaPlayer.create(getApplicationContext(), resID[songIndex]);
		mp.start(); // starting mediaplayer

	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		mp.release();


	}

	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);

		return true;

	}

	@Override

	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.action_settings) {

			return true;

		}

		return super.onOptionsItemSelected(item);

	}

}


package com.example.jsonparsingdemo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Saurastapragatimandal extends ListActivity {
	
	private static String url = "http://72samaj.com/application/adminpanel/kutchjson.php";
	
	private static final String TAG_CONTACTS = "kutch";
	private static final String TAG_ID = "id";
	private static final String TAG_HODDO = "name";
	private static final String TAG_NAME = "hoddo";
	private static final String TAG_GAM = "gam";
	private static final String TAG_CONTACT = "contact";
	//private static final String TAG_PHONE = "phone";
	//private static final String TAG_PHONE_MOBILE = "mobile";
	//private static final String TAG_PHONE_HOME = "home";
	//private static final String TAG_PHONE_OFFICE = "office";
	
	JSONArray contacts = null;
	
	ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        contactList = new ArrayList<HashMap<String,String>>();
        
        
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl(url);
        
        try{
        contacts = jsonObject.getJSONArray(TAG_CONTACTS);
		
		for(int i = 0; i < contacts.length(); i++){
			JSONObject c = contacts.getJSONObject(i);
			
			
			String id = c.getString(TAG_ID);
			String name = c.getString(TAG_NAME);
			String hoddo = c.getString(TAG_HODDO);
			String gam = "Vatan: "+c.getString(TAG_GAM);
			String contact = "Mobile: "+c.getString(TAG_CONTACT);
			
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(TAG_ID, id);
			map.put(TAG_NAME, name);
			map.put(TAG_HODDO, hoddo);
			map.put(TAG_GAM, gam);
			map.put(TAG_CONTACT, contact);

			// adding HashList to ArrayList
			contactList.add(map);
    
		}
        }catch (JSONException e){
        	e.printStackTrace();
        }
        
        ListAdapter adapter = new SimpleAdapter(this, contactList, R.layout.list_item, new String[] { TAG_NAME, TAG_HODDO, TAG_GAM,TAG_CONTACT }, new int[] {
				R.id.email, R.id.name, R.id.mobile,R.id.contact });
        
        setListAdapter(adapter);
        
        ListView lv1 = getListView();

		// Launching new screen on Selecting Single ListItem
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				String name = ((TextView) view.findViewById(R.id.email)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
				String contact = ((TextView) view.findViewById(R.id.contact)).getText().toString();
				
			
				Intent in = new Intent(MainActivity.this, SingleMenuItemActivity.class);
				in.putExtra(TAG_NAME, name);
				in.putExtra(TAG_HODDO, cost);
				in.putExtra(TAG_GAM		
						, description);
				in.putExtra(TAG_CONTACT, contact);
				startActivity(in);
*/
			}
		});
    }
}


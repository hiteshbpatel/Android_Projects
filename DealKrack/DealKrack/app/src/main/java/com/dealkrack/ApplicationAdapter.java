package com.dealkrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ApplicationAdapter extends ArrayAdapter<Application> {
	private List<Application> items;

	public ApplicationAdapter(Context context, List<Application> items) {
		super(context, R.layout.dealkrack, items);
		this.items = items;

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater li = LayoutInflater.from(getContext());
			v = li.inflate(R.layout.dealkrack, null);

		}

		Application app = items.get(position);

		if (app != null) {

			TextView name = (TextView) v.findViewById(R.id.name);
			TextView title=(TextView)v.findViewById(R.id.title);
			TextView code = (TextView) v.findViewById(R.id.code);
			TextView expiry= (TextView) v.findViewById(R.id.expiry);

			if (name != null)
				name.setText(app.getOffer_name());
			if (title != null)
				title.setText(app.getCoupon_title());
			
			if (code != null)
				code.setText(app.getCoupon_code());
			if (expiry != null)
				expiry.setText(app.getCoupon_expiry());

		}

		return v;
	}
}

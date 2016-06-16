package com.preghealth.lmnop.pregnancyhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chitkara Neetu on 5/15/2016.
 */
public class ContactsAdapter extends ArrayAdapter<Contacts> {

    Context context;
    ArrayList<Contacts> list;

    public ContactsAdapter(Context context, int resource, ArrayList<Contacts> list) {
        super(context, resource, list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView==null){
        convertView=inflater.inflate(R.layout.contactstemplate,null);
            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView email = (TextView)convertView.findViewById(R.id.tv_email);

            name.setText(list.get(position).getName());
            email.setText(list.get(position).getEmail());
        }
        return convertView;
    }
}

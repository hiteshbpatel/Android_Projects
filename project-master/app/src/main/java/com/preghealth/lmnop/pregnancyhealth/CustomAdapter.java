package com.preghealth.lmnop.pregnancyhealth;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chitkara Neetu on 5/12/2016.
 */
public class CustomAdapter extends ArrayAdapter<Parameter> {

    Context mcontext;
    int resource;
    ArrayList<Parameter> mparam;


    public CustomAdapter(Context mcontext, int resource, ArrayList<Parameter> mparam) {
        super(mcontext,resource,mparam);
        this.mcontext = mcontext;
        this.resource = resource;
        this.mparam = mparam;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null)
        {convertView=inflater.inflate(resource, null, true);
            TextView tvname= (TextView) convertView.findViewById(R.id.tv_name);
            TextView tvunit= (TextView)convertView.findViewById(R.id.tv_unit);
            ImageView imgview =(ImageView)convertView.findViewById(R.id.imageview);
            final TextView display= (TextView) convertView.findViewById(R.id.displayvalue);
            SeekBar seekBar= (SeekBar) convertView.findViewById(R.id.sb);

            tvname.setText(mparam.get(position).getName());
            tvunit.setText(mparam.get(position).getUnit());
            imgview.setImageResource(mparam.get(position).getImgid());

            double target=mparam.get(position).getTarget();
            display.setText(String.valueOf((int)target));
            seekBar.setProgress((int) target);
            seekBar.setMax((int)target);
            mparam.get(position).setActual((int)target);


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    display.setText(String.valueOf(progress));
                    mparam.get(position).setActual(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {                }
            });


        }

        return convertView;
    }

}

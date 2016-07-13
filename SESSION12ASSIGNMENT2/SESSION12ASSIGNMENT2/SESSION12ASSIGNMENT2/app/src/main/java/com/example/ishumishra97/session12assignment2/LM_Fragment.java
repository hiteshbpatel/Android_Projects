package com.example.ishumishra97.session12assignment2;

/**
 * Created by ishu.mishra97 on 6/29/2016.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LM_Fragment extends Fragment implements View.OnClickListener {
    Button btn;TextView tv1;
    EditText et1;
    String ishu;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.lm_fragment, container, false);
        et1=(EditText)rootView.findViewById(R.id.editText);
        tv1=(TextView)rootView.findViewById(R.id.textView);
        ishu=et1.getText().toString();
        btn=(Button)rootView.findViewById(R.id.button);
        btn.setOnClickListener(this);
    return rootView;
    }

    @Override
    public void onClick(View view) {
        tv1.setText(ishu);
    }
}

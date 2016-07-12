package info.acadgild.coursemenu;

import info.acadgild.coursemenu.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CloudComputingFragment extends Fragment {
	
	public CloudComputingFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_cluod_computing, container, false);
         
        return rootView;
    }
}

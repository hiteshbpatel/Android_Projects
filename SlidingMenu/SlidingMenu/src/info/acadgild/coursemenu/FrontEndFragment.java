package info.acadgild.coursemenu;

import info.acadgild.coursemenu.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FrontEndFragment extends Fragment {
	
	public FrontEndFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_front_end, container, false);
         
        return rootView;
    }
}

package com.pk.personalcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentManageItem extends Fragment
{
	SharedPreferences prefs;
	
	ImageView imagePreview;
	TextView textTitle;
	TextView textDescription;
	Button btnToggle;
	
	public static final FragmentManageItem newInstance(String title)
	{
	    FragmentManageItem f = new FragmentManageItem();
	    
	    Bundle bdl = new Bundle(1);
	    bdl.putString("Title", title);
	    f.setArguments(bdl);
	    
	    return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_manageitem, container, false);
		prefs = getActivity().getSharedPreferences("PersonalCalculatorPreferences", 0);
		String title = getArguments().getString("Title");
		
		imagePreview = (ImageView) view.findViewById(R.id.Image);
		textTitle = (TextView) view.findViewById(R.id.Title);
		textDescription = (TextView) view.findViewById(R.id.Description);
		btnToggle = (Button) view.findViewById(R.id.Toggle);
		
		textTitle.setText(title);
		
		return view;
	}
}

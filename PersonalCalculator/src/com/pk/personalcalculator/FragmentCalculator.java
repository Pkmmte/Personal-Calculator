package com.pk.personalcalculator;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FragmentCalculator extends Fragment
{
	int calculatorNum;
	
	public static final FragmentIntroduction newInstance(int type)
	{
		FragmentIntroduction f = new FragmentIntroduction();
		
		Bundle bdl = new Bundle(1);
		bdl.putInt("Calculator Number", type);
		f.setArguments(bdl);
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_calculator, container, false);
		
		calculatorNum = getArguments().getInt("Calculator Number");
		RelativeLayout Part1 = (RelativeLayout) view.findViewById(R.id.Intro1);
		RelativeLayout Part2 = (RelativeLayout) view.findViewById(R.id.Intro2);
		
		if(calculatorNum == 1)
			Part1.setVisibility(View.VISIBLE);
		else if(calculatorNum == 2)
			Part2.setVisibility(View.VISIBLE);
		
		return view;
	}
}

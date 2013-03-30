package com.pk.personalcalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class FragmentIntroduction extends Fragment
{
	int introNum;
	
	public static final FragmentIntroduction newInstance(int intro)
	{
		FragmentIntroduction f = new FragmentIntroduction();
		
		Bundle bdl = new Bundle(1);
		bdl.putInt("Intro Number", intro);
		f.setArguments(bdl);
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_introduction, container, false);
		
		introNum = getArguments().getInt("Intro Number");
		RelativeLayout Part1 = (RelativeLayout) view.findViewById(R.id.Intro1);
		RelativeLayout Part2 = (RelativeLayout) view.findViewById(R.id.Intro2);
		RelativeLayout Part3 = (RelativeLayout) view.findViewById(R.id.Intro3);
		RelativeLayout Part4 = (RelativeLayout) view.findViewById(R.id.Intro4);
		
		if(introNum == 1)
			Part1.setVisibility(View.VISIBLE);
		else if(introNum == 2)
			Part2.setVisibility(View.VISIBLE);
		else if(introNum == 3)
			Part3.setVisibility(View.VISIBLE);
		else if(introNum == 4)
			Part4.setVisibility(View.VISIBLE);
		
		return view;
	}
}

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
		final SharedPreferences prefs = getActivity().getBaseContext().getSharedPreferences("PersonalCalculatorPreferences", 0);
		RelativeLayout Part1 = (RelativeLayout) view.findViewById(R.id.Intro1);
		RelativeLayout Part2 = (RelativeLayout) view.findViewById(R.id.Intro2);
		RelativeLayout Part3 = (RelativeLayout) view.findViewById(R.id.Intro3);
		RelativeLayout Part4 = (RelativeLayout) view.findViewById(R.id.Intro4);
		ImageButton defaultThemeLight = (ImageButton) view.findViewById(R.id.imageTheme1);
		ImageButton defaultThemeDark = (ImageButton) view.findViewById(R.id.imageTheme2);
		final ImageView checkedThemeLight = (ImageView) view.findViewById(R.id.themeCheck1);
		final ImageView checkedThemeDark = (ImageView) view.findViewById(R.id.themeCheck2);
		
		if(introNum == 1)
			Part1.setVisibility(View.VISIBLE);
		else if(introNum == 2)
			Part2.setVisibility(View.VISIBLE);
		else if(introNum == 3)
			Part3.setVisibility(View.VISIBLE);
		else if(introNum == 4)
			Part4.setVisibility(View.VISIBLE);
		
		defaultThemeLight.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkedThemeLight.setVisibility(View.VISIBLE);
				checkedThemeDark.setVisibility(View.INVISIBLE);
				
				Editor editor = prefs.edit();
				editor.putInt("Theme", 0);
				editor.commit();
			}
		});
		defaultThemeDark.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkedThemeLight.setVisibility(View.INVISIBLE);
				checkedThemeDark.setVisibility(View.VISIBLE);
				
				Editor editor = prefs.edit();
				editor.putInt("Theme", 1);
				editor.commit();
			}
		});
		
		return view;
	}
}

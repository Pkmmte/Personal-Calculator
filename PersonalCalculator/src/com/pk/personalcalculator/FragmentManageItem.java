package com.pk.personalcalculator;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentManageItem extends Fragment
{
	SharedPreferences prefs;
	Editor editor;
	
	ImageView imagePreview;
	TextView textTitle;
	RelativeLayout themeSelector;
	static TextView textDescription;
	static Button btnToggle;
	
	boolean activated;
	boolean purchased;
	static int selectedTheme;
	static int themePosition;
	static String theme1;
	static String theme2;
	static String theme3;
	static String theme4;
	static String theme5;
	
	PagerContainer mContainer;
	ViewPager pager;
	
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
		editor = prefs.edit();
		final String title = getArguments().getString("Title");
		
		activated = prefs.getBoolean("Activated_" + title, false);
		purchased = prefs.getBoolean("Purchased_" + title, false);
		selectedTheme = prefs.getInt("Theme", 0);
		themePosition = selectedTheme;
		theme1 = getResources().getString(R.string.theme1_description);
		theme2 = getResources().getString(R.string.theme2_description);
		theme3 = getResources().getString(R.string.theme3_description);
		theme4 = getResources().getString(R.string.theme4_description);
		theme5 = getResources().getString(R.string.theme5_description);
		
		themeSelector = (RelativeLayout) view.findViewById(R.id.ThemeSelector);
		mContainer = (PagerContainer) view.findViewById(R.id.pager_container);
		imagePreview = (ImageView) view.findViewById(R.id.Image);
		textTitle = (TextView) view.findViewById(R.id.Title);
		textDescription = (TextView) view.findViewById(R.id.Description);
		btnToggle = (Button) view.findViewById(R.id.Toggle);
		pager = mContainer.getViewPager();
		pager.setOverScrollMode(View.OVER_SCROLL_NEVER);
		PagerAdapter adapter = new MyPagerAdapter();
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(adapter.getCount());
		pager.setPageMargin(15);
		pager.setClipChildren(false);
		pager.setCurrentItem(themePosition);
		
		textTitle.setText(title);
		switch (themePosition)
		{
			case 0:
				textDescription.setText(theme1);
				break;
			case 1:
				textDescription.setText(theme2);
				break;
			case 2:
				textDescription.setText(theme3);
				break;
			case 3:
				textDescription.setText(theme4);
				break;
			case 4:
				textDescription.setText(theme5);
				break;
		}
		
		if (title.equals("Themes"))
		{
			imagePreview.setVisibility(View.GONE);
			themeSelector.setVisibility(View.VISIBLE);
			
			// Default theme is already selected so..
			btnToggle.setText("Selected");
		}
		else
		{
			if (!purchased)
			{
				btnToggle.setBackgroundResource(R.drawable.button_green_selector);
				btnToggle.setText("Purchase");
			}
			else
			{
				if (activated)
					btnToggle.setText("Deactivate");
				else
					btnToggle.setText("Activate");
			}
		}
		
		btnToggle.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (title.equals("Themes"))
				{
					if(themePosition == selectedTheme)
						Toast.makeText(getActivity().getBaseContext(), "You already have this theme selected!", Toast.LENGTH_SHORT).show();
					else
					{
						selectedTheme = themePosition;
						
						Editor editor = prefs.edit();
						editor.putInt("Theme", selectedTheme);
						editor.commit();
						
						btnToggle.setText("Selected");
					}
				}
				else
				{
					if (!purchased)
					{
						
					}
					else
					{
						if (activated)
						{
							activated = false;
							btnToggle.setText("Activate");
							editor.putBoolean("Activated_" + title, activated);
							editor.commit();
						}
						else
						{
							activated = true;
							btnToggle.setText("Deactivate");
							editor.putBoolean("Activated_" + title, activated);
							editor.commit();
						}
					}
				}
			}
		});
		
		
		
		return view;
	}
	
	public static void onViewPagerChange(int position)
	{
		themePosition = position;
		
		switch (themePosition)
		{
			case 0:
				textDescription.setText(theme1);
				break;
			case 1:
				textDescription.setText(theme2);
				break;
			case 2:
				textDescription.setText(theme3);
				break;
			case 3:
				textDescription.setText(theme4);
				break;
			case 4:
				textDescription.setText(theme5);
				break;
		}
		
		if(themePosition != selectedTheme)
			btnToggle.setText("Select");
		else
			btnToggle.setText("Selected");
	}
	
	private class MyPagerAdapter extends PagerAdapter
	{
		
		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			TextView view = new TextView(getActivity().getBaseContext());
			view.setText("Item " + position);
			view.setGravity(Gravity.CENTER);
			view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
			
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}
		
		@Override
		public int getCount()
		{
			return 5;
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return (view == object);
		}
	}
}

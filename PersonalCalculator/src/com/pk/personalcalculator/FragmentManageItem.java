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
import android.widget.TextView;

public class FragmentManageItem extends Fragment
{
	SharedPreferences prefs;
	Editor editor;
	
	ImageView imagePreview;
	TextView textTitle;
	TextView textDescription;
	Button btnToggle;
	
	boolean activated;
	boolean purchased;
	
	PagerContainer mContainer;
	
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
		
		imagePreview = (ImageView) view.findViewById(R.id.Image);
		textTitle = (TextView) view.findViewById(R.id.Title);
		textDescription = (TextView) view.findViewById(R.id.Description);
		btnToggle = (Button) view.findViewById(R.id.Toggle);
		
		textTitle.setText(title);
		
		if (!purchased)
		{
			btnToggle.setBackgroundResource(R.drawable.button_green_selector);
			btnToggle.setText("Purchase");
		}
		else
		// Yes, I like using nested if statements!
		{
			if (activated)
				btnToggle.setText("Deactivate");
			else
				btnToggle.setText("Activate");
		}
		
		btnToggle.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
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
		});
		
		mContainer = (PagerContainer) view.findViewById(R.id.pager_container);
		 
        ViewPager pager = mContainer.getViewPager();
        pager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        pager.setPageMargin(15);
 
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
		
		return view;
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

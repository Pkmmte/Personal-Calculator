package com.pk.personalcalculator;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	Editor editor;
	
	ImageView imagePreview;
	TextView textTitle;
	TextView textDescription;
	Button btnToggle;
	
	boolean activated;
	boolean purchased;
	
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
		
		return view;
	}
}

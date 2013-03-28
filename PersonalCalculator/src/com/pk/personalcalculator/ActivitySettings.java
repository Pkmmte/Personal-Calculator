package com.pk.personalcalculator;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivitySettings extends ListActivity
{
	List<SettingsItem> listOfItems;
	SettingsAdapter adapter;
	SharedPreferences prefs;
	
	MenuItem mItemManage;
	MenuItem mItemSettings;
	MenuItem mItemDebug;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		
		/** EDIT LATER **/
		listOfItems = new ArrayList<SettingsItem>();
		listOfItems.add(new SettingsItem("Notification Bar", "Show download progress on notification bar.", "Setting", "True", "CheckBox"));
		listOfItems.add(new SettingsItem("WiFi-Only Update", "Update video list only when connected to WiFi.", "Setting", "False", "CheckBox"));
		listOfItems.add(new SettingsItem("Update Interval", "Choose how often to check for new videos.", "Setting", "Value 2", "Text"));
		listOfItems.add(new SettingsItem("Advanced", "Collapsed"));
		
		adapter = new SettingsAdapter(ActivitySettings.this, listOfItems);
		
		setListAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		this.mItemManage = menu.findItem(R.id.action_manage);
		this.mItemSettings = menu.findItem(R.id.action_settings);
		this.mItemDebug = menu.findItem(R.id.action_debug);
		
		// This activity doesn't need these two items shown...
		mItemManage.setVisible(false);
		mItemSettings.setVisible(false);
		
		// If on debug mode, let us debug!
		if(ActivityMain.DEBUG)
			mItemDebug.setVisible(true);
		else
			mItemDebug.setVisible(false);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_debug:
				startActivity(new Intent(ActivitySettings.this, ActivityDebug.class));
				return true;
			default:

				return super.onOptionsItemSelected(item);
		}
	}
}
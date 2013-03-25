package com.pk.personalcalculator;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ActivitySettings extends ListActivity
{
	List<SettingsItem> listOfItems;
	SettingsAdapter adapter;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		prefs = getSharedPreferences("KhanAcademyPreferences", 0);
		
		/** EDIT LATER **/
		listOfItems = new ArrayList<SettingsItem>();
		listOfItems.add(new SettingsItem("Notification Bar", "Show download progress on notification bar.", "Setting", "True", "CheckBox"));
		listOfItems.add(new SettingsItem("WiFi-Only Update", "Update video list only when connected to WiFi.", "Setting", "False", "CheckBox"));
		listOfItems.add(new SettingsItem("Update Interval", "Choose how often to check for new videos.", "Setting", "Value 2", "Text"));
		listOfItems.add(new SettingsItem("Advanced", "Collapsed"));
		
		adapter = new SettingsAdapter(ActivitySettings.this, listOfItems);
		
		setListAdapter(adapter);
	}
}
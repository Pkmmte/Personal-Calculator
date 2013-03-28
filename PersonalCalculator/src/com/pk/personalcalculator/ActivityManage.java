package com.pk.personalcalculator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityManage extends Activity
{
	ActionBar actionBar;
	private SharedPreferences prefs;
	
	Fragment fragList;
	Fragment fragItem;
	FragmentManager fm;
	FragmentTransaction transaction;
	
	MenuItem mItemManage;
	MenuItem mItemSettings;
	MenuItem mItemDebug;
	int onPage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		onPage = 1;
	}
	
	// Create ActionBar menu options
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		this.mItemManage = menu.findItem(R.id.action_manage);
		this.mItemSettings = menu.findItem(R.id.action_settings);
		this.mItemDebug = menu.findItem(R.id.action_debug);
		
		// This manage activity doesn't need these two items shown...
		mItemManage.setVisible(false);
		mItemSettings.setVisible(false);
		
		// If on debug mode, let us debug!
		if(ActivityMain.DEBUG)
			mItemDebug.setVisible(true);
		else
			mItemDebug.setVisible(false);
		
		return true;
	}
	
	// Select what to do once ActionBar option is touched.
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				if(onPage == 1)
					finish();
				else
				{
					onPage = 1;
					
				}
				return true;
			case R.id.action_debug:
				Intent debugIntent = new Intent(ActivityManage.this, ActivityDebug.class);
				startActivity(debugIntent);
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}

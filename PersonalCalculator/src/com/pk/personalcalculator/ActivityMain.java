package com.pk.personalcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ActivityMain extends FragmentActivity
{
	// For debugging purposes. Remember to set to false if released.
	final static Boolean DebugMode = true;
	boolean firstTime;
	boolean lockdownEnabled;
	private SharedPreferences prefs;

	Fragment fragLockdown;
	static Fragment fragIntroduction;
	static FragmentManager fm;
	static FragmentTransaction transaction;
	
	MenuItem mItemManage;
	MenuItem mItemSettings;
	MenuItem mItemDebug;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		fm = getSupportFragmentManager();
		
		firstTime = prefs.getBoolean("First Time", true);
		lockdownEnabled = prefs.getBoolean("Activated_Lockdown", false);
		if(firstTime)
			showIntroduction();
		else if(lockdownEnabled)
			showLockdown();
		else
			startActivity(new Intent(ActivityMain.this, ActivityCalculator.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);

		this.mItemManage = menu.findItem(R.id.action_manage);
		this.mItemSettings = menu.findItem(R.id.action_settings);
		this.mItemDebug = menu.findItem(R.id.action_debug);
		
		// If this is their first time, ignore the action bar menu
		if(firstTime)
		{
			mItemManage.setVisible(false);
			mItemSettings.setVisible(false);
		}
		
		// If on debug mode, let us debug!
		if(ActivityMain.DebugMode)
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
			case R.id.action_manage:
				Intent manageIntent = new Intent(ActivityMain.this, ActivityManage.class);
				startActivity(manageIntent);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(ActivityMain.this, ActivitySettings.class);
				startActivity(settingsIntent);
				return true;
			case R.id.action_debug:
				startActivity(new Intent(ActivityMain.this, ActivityDebug.class));
			default:
				
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void showIntroduction()
	{
		setDefaults();

		fragIntroduction = FragmentIntroduction.newInstance(1);
		transaction = fm.beginTransaction();
		transaction.replace(R.id.Frame, fragIntroduction);
		transaction.commit();
	}
	
	public void showLockdown()
	{
		fragLockdown = new FragmentLockdown();
		transaction = fm.beginTransaction();
		transaction.replace(R.id.Frame, fragLockdown);
		transaction.commit();
	}
	
	public void onStartClick(View v)
	{
		FragmentLockdown.onStartClick(ActivityMain.this);
	}
	
	public void onSkipClick(View v)
	{
		Intent skipIntent = new Intent(ActivityMain.this, ActivityCalculator.class);
		skipIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(skipIntent);
	}
	
	public void onNextClick(View v)
	{
		int pageNum = Integer.parseInt(v.getTag().toString());
		
		if(pageNum == 4)
		{
			Intent skipIntent = new Intent(ActivityMain.this, ActivityCalculator.class);
			skipIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(skipIntent);
		}
		else
		{
			fragIntroduction = FragmentIntroduction.newInstance(pageNum + 1);
			transaction = fm.beginTransaction();
<<<<<<< HEAD
=======
			transaction.setCustomAnimations(R.anim.fancy_in_from_right, R.anim.fancy_out_to_left, R.anim.fancy_in_from_left, R.anim.fancy_out_to_right);
>>>>>>> 0417dc9254b0ec8a76a0fe897759bc860e26c2fa
			transaction.replace(R.id.Frame, fragIntroduction);
			transaction.commit();
		}
	}
	
	public void setDefaults()
	{
		Editor editor = prefs.edit();
		
		// This is no longer the first time
		editor.putBoolean("First Time", false);
		
		// Set purchased values. Some are free and some are paid.
		editor.putBoolean("Purchased_Themes", true);
		editor.putBoolean("Purchased_Lockdown", true);
		editor.putBoolean("Purchased_Widget", false);
		editor.putBoolean("Purchased_Pop Up", false);
		
		// Set activated defaults. Lockdown is deactivated by default.
		editor.putBoolean("Activated_Themes", true);
		editor.putBoolean("Activated_Lockdown", false);
		editor.putBoolean("Activated_Widget", true);
		editor.putBoolean("Activated_Pop Up", true);
		
		// Settings
		
		editor.commit();
	}
}
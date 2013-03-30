package com.pk.personalcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

public class ActivityMain extends Activity
{
	// For debugging purposes. Remember to set to false if released. (Even a
	// public beta)
	final static Boolean DebugMode = true;

	private SharedPreferences prefs;
	int Hours;
	int Minutes;

	MenuItem mItemDebug;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		final TimePicker timer = (TimePicker)findViewById(R.id.timer);
		timer.setIs24HourView(true);
		final Button start = (Button)findViewById(R.id.start); // This is somehow assigned as NULL.
		
		boolean firstTime = prefs.getBoolean("First Time", true);
		if(firstTime)
			showIntroduction();
		
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Hours = timer.getCurrentHour();
				Minutes = timer.getCurrentMinute();
				
				String confirmMessage;
				String youCrazy = "\n(That's a long time..)";
				String areYouHigh = "\n(That's a VERY long time!)";
				if(Hours > 0)
					confirmMessage = "Are you sure you wish to lock down your phone for " + Hours + " hours and " + Minutes + " minutes?";
				else
					confirmMessage = "Are you sure you wish to lock down your phone for " + Minutes + " minutes?";
				if (Hours > 12)
					confirmMessage += areYouHigh;
				else if (Hours > 5)
					confirmMessage += youCrazy;
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityMain.this);
				
				// set title
				alertDialogBuilder.setTitle("Confirm");
				
				// set dialog message
				alertDialogBuilder.setMessage(confirmMessage);
				alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{
						// if this button is clicked, close
						// current activity
						Intent intent = new Intent(ActivityMain.this, ActivityCalculator.class);
						startActivity(intent);
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
				
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
				
				// show it
				alertDialog.show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		this.mItemDebug = menu.findItem(R.id.action_debug);
		
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
	}
	
	public void showLockdown()
	{
		
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
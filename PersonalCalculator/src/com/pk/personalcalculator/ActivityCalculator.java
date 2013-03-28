package com.pk.personalcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ActivityCalculator extends Activity
{

	private SharedPreferences prefs;
	// Fonts used to Google Now theme.
	Typeface robotoThin;
	Typeface robotoBoldCondensed;
	
	// Buttons...
	Button btnClear;
	Button btn0;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	
	// Called when activity starts
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);

		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		
		initializeUI();
		lockdown();
	}
	
	// Create ActionBar menu options
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}
	
	// Select what to do once ActionBar option is touched.
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_manage:
				Intent manageIntent = new Intent(ActivityCalculator.this, ActivityManage.class);
				startActivity(manageIntent);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(ActivityCalculator.this, ActivitySettings.class);
				startActivity(settingsIntent);
				return true;
			case R.id.action_debug:
				startActivity(new Intent(ActivityCalculator.this, ActivityDebug.class));
			default:

				return super.onOptionsItemSelected(item);
		}
	}
	
	// Initialize all UI objects and set theme if needed
	public void initializeUI()
	{
		robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");
		
		btnClear = (Button) findViewById(R.id.btnClear);
		btn0 = (Button) findViewById(R.id.btn0);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		
		btnClear.setTypeface(robotoBoldCondensed);
		btn0.setTypeface(robotoThin);
		btn1.setTypeface(robotoThin);
		btn2.setTypeface(robotoThin);
		btn3.setTypeface(robotoThin);
		btn4.setTypeface(robotoThin);
		btn5.setTypeface(robotoThin);
		btn6.setTypeface(robotoThin);
		btn7.setTypeface(robotoThin);
		btn8.setTypeface(robotoThin);
		btn9.setTypeface(robotoThin);
	}
	
	// Lock down the system
	public void lockdown()
	{
		View disableStatusBar = new View(ActivityCalculator.this);
		
		WindowManager.LayoutParams handleParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, 50,
		// This allows the view to be displayed over the status bar
		WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
		// this is to keep button presses going to the background window
		WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
		// this is to enable the notification to recieve touch events
		WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
		// Draws over status bar
		WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, PixelFormat.TRANSLUCENT);
		
		handleParams.gravity = Gravity.TOP;
		getWindow().addContentView(disableStatusBar, handleParams);
	}
}
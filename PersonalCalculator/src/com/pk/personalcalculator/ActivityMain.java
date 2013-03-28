package com.pk.personalcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

/** DON'T FORGET TO MODIFY THE CHANGELOG AT THE END OF THIS FILE **/

public class ActivityMain extends Activity{
	TimePicker timer;
	CheckBox noTimer;
	Button Start;
	
	int Hours;
	int Minutes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		final TimePicker timer = (TimePicker) findViewById(R.id.timer);
		timer.setIs24HourView(true);
		Start = (Button) findViewById(R.id.start);
		noTimer = (CheckBox) findViewById(R.id.noLock);
		
		Start.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Hours = timer.getCurrentHour();
				Minutes = timer.getCurrentMinute();
				
				String youCrazy = " (That's a long time..)";
				String areYouHigh = " (That's a VERY long time!)";
				String confirmMessage = "Are you sure you wish to lock down your phone for " + Hours + " hours and " + Minutes + " minutes?";
				if(Hours > 12)
					confirmMessage += areYouHigh;
				else if(Hours > 5)
					confirmMessage += youCrazy;
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityMain.this);
				
				// set title
				alertDialogBuilder.setTitle("Confirm");
				
				// set dialog message
				alertDialogBuilder.setMessage(confirmMessage);
				alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						// if this button is clicked, close
						// current activity
						Intent intent = new Intent(ActivityMain.this, ActivityCalculator.class);
						startActivity(intent);
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
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
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.action_manage:
				Intent manageIntent = new Intent(ActivityMain.this, ActivityManage.class);
				startActivity(manageIntent);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(ActivityMain.this, ActivitySettings.class);
				startActivity(settingsIntent);
				return true;
			default:

				return super.onOptionsItemSelected(item);
		}
	}
}

/******* CHANGELOG *******/
/* Add your changes here../
 * 
 * Jerry: 
 * -(3/27) Added variable & instantiation for the EditText field "Input" (R.id.Input)
 * -(3/27) Added void method buttonClickHandler, which expects a parameter V of type 'View'.
 * -(3/27) Added a switch statement to the "buttonClickHandler" method, which will decide what will happen when a specific button is pressed.
 * -(3/27) Added a method stub 'solve', which will take the characters in 'Input' and create an equation which the machine will solve.
 * -(3/27) Some code clean up
 * -MESSAGE: I created a text file called "CHANGELOG" if you want the changes to be recorded there instead.
 * 
 * Pkmmte:
 * - (3/23) Create Manage activity to manage all plugins and themes.
 * - (3/23) Cleaned up code.jfghfghfghfghfghfghgfhfgh
 */
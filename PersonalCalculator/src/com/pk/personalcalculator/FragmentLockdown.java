package com.pk.personalcalculator;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

public class FragmentLockdown extends Fragment
{
	static TimePicker timer;
	
	static int Hours;
	static int Minutes;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_lockdown, container, false);
		
		timer = (TimePicker) view.findViewById(R.id.timer);
		timer.setIs24HourView(true);
		
		return view;
	}
	
	public static void onStartClick(final Context context)
	{
		// Get timer values
		Hours = timer.getCurrentHour();
		Minutes = timer.getCurrentMinute();
		
		// Build confirmation message
		String confirmMessage;
		String youCrazy = "\n(That's a long time..)";
		String areYouHigh = "\n(That's a VERY long time!)";
		if (Hours > 0)
			confirmMessage = "Are you sure you wish to lock down your phone for " + Hours + " hours and " + Minutes + " minutes?";
		else
			confirmMessage = "Are you sure you wish to lock down your phone for " + Minutes + " minutes?";
		if (Hours > 12)
			confirmMessage += areYouHigh;
		else if (Hours > 5)
			confirmMessage += youCrazy;
		
		if (Hours > 0 || Minutes > 0)
		{
			// Declare & Initialize builder for confirmation dialog
			Builder confirmDialogBuilder = new AlertDialog.Builder(context);
			
			// Set dialog properties
			confirmDialogBuilder.setTitle("Confirm");
			confirmDialogBuilder.setMessage(confirmMessage);
			confirmDialogBuilder.setCancelable(false);
			
			// Button functions
			confirmDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int id)
				{
					Intent lockIntent = new Intent(context, ActivityCalculator.class);
					lockIntent.putExtra("Lockdown", true);
					lockIntent.putExtra("Hours", Hours);
					lockIntent.putExtra("Minutes", Minutes);
					lockIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(lockIntent);
				}
			});
			confirmDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int id)
				{
					dialog.cancel();
				}
			});
			
			// Create & show dialog
			AlertDialog confirmDialog = confirmDialogBuilder.create();
			confirmDialog.show();
		}
	}
}

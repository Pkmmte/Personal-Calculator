package com.pk.personalcalculator;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityDebug extends Activity
{
	private SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug);
		
		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
	}
	
	public void clearApplicationData()
	{
		File cache = getCacheDir();
		File appDir = new File(cache.getParent());
		if (appDir.exists())
		{
			String[] children = appDir.list();
			for (String s : children)
			{
				if (!s.equals("lib"))
				{
					deleteDir(new File(appDir, s));
					Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
				}
			}
		}
	}
	
	public static boolean deleteDir(File dir)
	{
		if (dir != null && dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}
		
		return dir.delete();
	}
	
	public void clearData(View v)
	{
		clearApplicationData();
	}
	
	public void forceStop(View v)
	{
		System.exit(0);
	}
	
	public void resetDefault(View v)
	{
		Editor editor = prefs.edit();
		
		editor.putBoolean("First Time", false);
		editor.putBoolean("Purchased_Themes", true);
		editor.putBoolean("Purchased_Lockdown", true);
		editor.putBoolean("Purchased_Widget", false);
		editor.putBoolean("Purchased_Pop Up", false);
		editor.putBoolean("Activated_Themes", true);
		editor.putBoolean("Activated_Lockdown", false);
		editor.putBoolean("Activated_Widget", true);
		editor.putBoolean("Activated_Pop Up", true);
		
		editor.commit();
	}
}

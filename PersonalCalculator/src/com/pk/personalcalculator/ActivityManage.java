package com.pk.personalcalculator;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityManage extends FragmentActivity
{
	ActionBar actionBar;
	private SharedPreferences prefs;
	
	static Fragment fragList;
	static Fragment fragItem;
	static FragmentManager fm;
	static FragmentTransaction transaction;
	
	MenuItem mItemManage;
	MenuItem mItemSettings;
	MenuItem mItemDebug;
	static int onPage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		onPage = 1;
		
		fragList = new FragmentManageList();
		fm = getSupportFragmentManager();
		transaction = fm.beginTransaction();
		transaction.replace(R.id.Frame, fragList);
		transaction.commit();
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
		
		// This activity doesn't need these two items shown...
		mItemManage.setVisible(false);
		mItemSettings.setVisible(false);
		
		// If on debug mode, let us debug!
		if (ActivityMain.DebugMode)
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
				if (onPage == 1)
					finish();
				else
				{
					onPage = 1;
					transaction = fm.beginTransaction();
					transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right, R.anim.in_from_right, R.anim.out_to_left);
					transaction.replace(R.id.Frame, fragList);
					transaction.commit();
				}
				return true;
			case R.id.action_debug:
				startActivity(new Intent(ActivityManage.this, ActivityDebug.class));
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (onPage == 1)
				finish();
			else
			{
				onPage = 1;
				transaction = fm.beginTransaction();
				transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right, R.anim.in_from_right, R.anim.out_to_left);
				transaction.replace(R.id.Frame, fragList);
				transaction.commit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public static void switchFragment(String Data, Context context)
	{
		onPage++;
		fragItem = FragmentManageItem.newInstance(Data);
		transaction = fm.beginTransaction();
		transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
		transaction.replace(R.id.Frame, fragItem);
		transaction.commit();
	}
}

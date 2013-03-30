package com.pk.personalcalculator;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
		if (ActivityMain.DebugMode)
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
	
	public class SettingsAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;
		
		private List<SettingsItem> listItem;
		
		public SettingsAdapter(Context context, List<SettingsItem> listItem)
		{
			this.context = context;
			this.listItem = listItem;
		}
		
		@Override
		public int getCount()
		{
			return listItem.size();
		}
		
		@Override
		public Object getItem(int position)
		{
			return listItem.get(position);
		}
		
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		
		@Override
		public View getView(int position, View view, ViewGroup viewGroup)
		{
			SettingsItem entry = listItem.get(position);
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.settings_item, null);
			}
			
			// Get Values
			String Name = entry.getName();
			String Description = entry.getDescription();
			String Type = entry.getType();
			String Value = entry.getValue();
			String ValueType = entry.getValueType();
			
			// Types
			RelativeLayout LayoutSetting = (RelativeLayout) view.findViewById(R.id.Setting);
			RelativeLayout LayoutAdvanced = (RelativeLayout) view.findViewById(R.id.Advanced);
			TextView LayoutText = (TextView) view.findViewById(R.id.Text);
			
			// Setting
			TextView textName = (TextView) view.findViewById(R.id.Name);
			TextView textDescription = (TextView) view.findViewById(R.id.Description);
			ImageView checkValue = (ImageView) view.findViewById(R.id.checkBox);
			TextView textValue = (TextView) view.findViewById(R.id.textValue);
			
			// Advanced
			ImageView EXP_COL = (ImageView) view.findViewById(R.id.EXP_COL);
			
			if (Type.equals("Setting"))
			{
				LayoutSetting.setVisibility(View.VISIBLE);
				LayoutAdvanced.setVisibility(View.GONE);
				LayoutText.setVisibility(View.GONE);
				
				textName.setText(Name);
				textDescription.setText(Description);
				if (ValueType.equals("Text"))
				{
					checkValue.setVisibility(View.GONE);
					textValue.setVisibility(View.VISIBLE);
					
					textValue.setText(Value);
				}
				else
				{
					checkValue.setVisibility(View.VISIBLE);
					textValue.setVisibility(View.GONE);
					
					if (Value.equals("True"))
						checkValue.setImageResource(R.drawable.checkbox_on);
					else
						checkValue.setImageResource(R.drawable.checkbox_off);
				}
			}
			else if (Type.equals("Advanced"))
			{
				LayoutSetting.setVisibility(View.GONE);
				LayoutAdvanced.setVisibility(View.VISIBLE);
				LayoutText.setVisibility(View.GONE);
				
				if (Value.equals("Expanded"))
					EXP_COL.setImageResource(R.drawable.collapse);
				else
					EXP_COL.setImageResource(R.drawable.expand);
			}
			else if (Type.equals("Text"))
			{
				LayoutSetting.setVisibility(View.GONE);
				LayoutAdvanced.setVisibility(View.GONE);
				LayoutText.setVisibility(View.VISIBLE);
				
				LayoutText.setText(Value);
			}
			
			if (Name.equals("Sort Downloads") || Name.equals("Download Location") || Name.equals("Download Limit"))
				LayoutSetting.setAlpha((float) 0.3);
			else
				LayoutSetting.setAlpha(1);
			
			return view;
		}
		
		@Override
		public void onClick(View view)
		{
			String entry = (String) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();
			
		}
	}
	
	public class SettingsItem
	{
		String Name;
		String Description;
		String Type;
		String Value;
		String ValueType;
		
		// Setting
		public SettingsItem(String Name, String Description, String Type,
				String Value, String ValueType)
		{
			this.Name = Name;
			this.Description = Description;
			this.Type = Type;
			this.Value = Value;
			this.ValueType = ValueType;
		}
		
		// Advanced & Other
		public SettingsItem(String Type, String Value)
		{
			this.Name = "";
			this.Description = "";
			this.Type = Type;
			this.Value = Value;
			this.ValueType = "";
		}
		
		// Pro
		public SettingsItem(String Type)
		{
			this.Name = "";
			this.Description = "";
			this.Type = Type;
			this.Value = "";
			this.ValueType = "";
		}
		
		public String getName()
		{
			return Name;
		}
		
		public String getDescription()
		{
			return Description;
		}
		
		public String getType()
		{
			return Type;
		}
		
		public String getValue()
		{
			return Value;
		}
		
		public String getValueType()
		{
			return ValueType;
		}
	}
}
package com.pk.personalcalculator;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
		
		if(Type.equals("Setting"))
		{
			LayoutSetting.setVisibility(View.VISIBLE);
			LayoutAdvanced.setVisibility(View.GONE);
			LayoutText.setVisibility(View.GONE);
			
			textName.setText(Name);
			textDescription.setText(Description);
			if(ValueType.equals("Text"))
			{
				checkValue.setVisibility(View.GONE);
				textValue.setVisibility(View.VISIBLE);
				
				textValue.setText(Value);
			}
			else
			{
				checkValue.setVisibility(View.VISIBLE);
				textValue.setVisibility(View.GONE);
				
				if(Value.equals("True"))
					checkValue.setImageResource(R.drawable.checkbox_on);
				else
					checkValue.setImageResource(R.drawable.checkbox_off);
			}
		}
		else if(Type.equals("Advanced"))
		{
			LayoutSetting.setVisibility(View.GONE);
			LayoutAdvanced.setVisibility(View.VISIBLE);
			LayoutText.setVisibility(View.GONE);
			
			if(Value.equals("Expanded"))
				EXP_COL.setImageResource(R.drawable.collapse);
			else
				EXP_COL.setImageResource(R.drawable.expand);
		}
		else if(Type.equals("Text"))
		{
			LayoutSetting.setVisibility(View.GONE);
			LayoutAdvanced.setVisibility(View.GONE);
			LayoutText.setVisibility(View.VISIBLE);
			
			LayoutText.setText(Value);
		}
		
		if(Name.equals("Sort Downloads") || Name.equals("Download Location") || Name.equals("Download Limit"))
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
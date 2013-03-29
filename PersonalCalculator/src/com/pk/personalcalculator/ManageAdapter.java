package com.pk.personalcalculator;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ManageAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;

	private List<String> listItem;

	public ManageAdapter(Context context, List<String> listItem)
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
		String entry = listItem.get(position);
		if (view == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.manage_item, null);
		}
		
		Resources res = context.getResources();
		
		// Types
		TextView Title = (TextView) view.findViewById(R.id.Title);
		ImageView Image = (ImageView) view.findViewById(R.id.Image);
		TextView Description = (TextView) view.findViewById(R.id.Description);
		
		Title.setText(entry);
		
		if(entry.equals("Themes"))
			Description.setText(res.getString(R.string.themes_description));
		else if(entry.equals("Lockdown"))
			Description.setText(res.getString(R.string.lockdown_description));
		else if(entry.equals("Widget"))
			Description.setText(res.getString(R.string.widget_description));
		else if(entry.equals("Pop Up"))
			Description.setText(res.getString(R.string.popup_description));
		else if(entry.equals("Graphing"))
			Description.setText(res.getString(R.string.graphing_description));
		
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
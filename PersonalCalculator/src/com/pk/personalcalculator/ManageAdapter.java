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

public class ManageAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;

	private List<String> listItem;

	public ManageAdapter(Context context, List<String> listItem)
	{
		this.context = context;
		this.listItem = listItem;
	}

	public int getCount()
	{
		return listItem.size();
	}

	public Object getItem(int position)
	{
		return listItem.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View view, ViewGroup viewGroup)
	{
		String entry = listItem.get(position);
		if (view == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.manage_item, null);
		}
		
		// Types
		TextView Title = (TextView) view.findViewById(R.id.Title);
		ImageView Image = (ImageView) view.findViewById(R.id.Image);
		TextView Description = (TextView) view.findViewById(R.id.Description);
		
		Title.setText(entry);
		
		
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
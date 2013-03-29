package com.pk.personalcalculator;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentManageList extends Fragment
{
	List<String> listOfItems;
	ManageAdapter adapter;
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_managelist, container, false);
		
		list = (ListView) view.findViewById(R.id.ListView);
		
		listOfItems = new ArrayList<String>();
		listOfItems.add("Themes");
		listOfItems.add("Lockdown");
		listOfItems.add("Widget");
		listOfItems.add("Pop Up");
		listOfItems.add("Graphing");
		
		adapter = new ManageAdapter(getActivity().getBaseContext(), listOfItems);
		
		list.setAdapter(adapter);
		
		return view;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		list.setDividerHeight(0);
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				String ID = listOfItems.get(position);
				ActivityManage.switchFragment(ID , getActivity());
			}
		});
	}
}

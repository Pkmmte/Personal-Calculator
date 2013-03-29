package com.pk.personalcalculator;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentManageChoices extends Fragment
{
	List<String> listOfItems;
	ManageAdapter adapter;
	SharedPreferences prefs;
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_managelist, container, false);
		prefs = getActivity().getSharedPreferences("PersonalCalculatorPreferences", 0);
		
		list = (ListView) view.findViewById(R.id.ListView);
		
		listOfItems = new ArrayList<String>();
		listOfItems.add("Themes");
		listOfItems.add("Lockdown");
		listOfItems.add("Widget");
		listOfItems.add("Pop Up");
		listOfItems.add("Graphing");
		
		adapter = new ManageAdapter(getActivity().getBaseContext(), listOfItems);
		
		list.setDividerHeight(0);
		list.setAdapter(adapter);
		
		return view;
	}
}

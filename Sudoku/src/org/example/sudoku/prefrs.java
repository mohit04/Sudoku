package org.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class prefrs extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	public static boolean getHints(Context context)
	{
		boolean bool = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("hints", true);
		//Log.i("get_hints",);
		return bool;
	}
	
  
}

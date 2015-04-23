package org.example.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View continuebutton = findViewById(R.id.button1); 
		continuebutton.setOnClickListener(this);
		View newgamebutton = findViewById(R.id.button2); 
		newgamebutton.setOnClickListener(this);
		View aboutbutton = findViewById(R.id.button3); 
		aboutbutton.setOnClickListener(this);
		View exitbutton = findViewById(R.id.button4); 
		exitbutton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		//setContentView(R.menu.main);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 super.onOptionsItemSelected(item);
		switch(item.getItemId()) {
		case R.id.action_settings:
			
			Intent i =new Intent(this,prefrs.class);
			startActivity(i);
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button3:
			Intent i = new Intent(this,about.class);
			startActivity(i);
	        break;
		case R.id.button2:
			open_ng_dialog();
		}
	}
	
	public void open_ng_dialog() {
			
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Difficulty");
		builder.setItems(new String[]{"Easy","Medium","Hard"},new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				startgame(arg1);
			}
		});	
		AlertDialog alertd=builder.create();
		alertd.show();
	}

	public void startgame(int i) {
		Log.d("new_game_dialog_tag","clicked on"+i);
		Intent in=new Intent(this,Game.class);
		in.putExtra("difficulty",i);
		startActivity(in);
	}
}

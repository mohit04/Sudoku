package org.example.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class Keypad extends Dialog {
	
	 private final int useds[];
	   private final PuzzleView puzzleView;
	   
	   private final View keys[] = new View[10];
	   private View keypad;


	public Keypad(Context context,int[] tiles,PuzzleView puzzleView) {
		super(context);
		// TODO Auto-generated constructor stub
		 this.useds = tiles;
	      this.puzzleView = puzzleView;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("KeyPad");
	      setContentView(R.layout.keypad);
	      findViews();
	      for (int element : useds) {
	         if (element != 0)
	            keys[element - 1].setVisibility(View.INVISIBLE);
	      }
	      
	      setListeners();
	      
	}
	
	 private void findViews() {
	      keypad = findViewById(R.id.keypad);
	      keys[0] = findViewById(R.id.keypad_1);
	      keys[1] = findViewById(R.id.keypad_2);
	      keys[2] = findViewById(R.id.keypad_3);
	      keys[3] = findViewById(R.id.keypad_4);
	      keys[4] = findViewById(R.id.keypad_5);
	      keys[5] = findViewById(R.id.keypad_6);
	      keys[6] = findViewById(R.id.keypad_7);
	      keys[7] = findViewById(R.id.keypad_8);
	      keys[8] = findViewById(R.id.keypad_9);
	      keys[9] = findViewById(R.id.keypad_11);
	   }
	 
	 private void returnResult(int tile) {
		 
	      puzzleView.setSelectedTile(tile);
	      dismiss();
	   }
	 
	 private void setListeners() {
	      for (int i = 0; i < keys.length; i++) {
	         final int t = i + 1;
	         keys[i].setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v) {
	            	if(t==10)
	            		returnResult(0);
	            	else
	               returnResult(t);
	            }});
	      }
	      keypad.setOnClickListener(new View.OnClickListener(){
	         public void onClick(View v) {
	          //  returnResult(0);
	         }});
	   }

	
}

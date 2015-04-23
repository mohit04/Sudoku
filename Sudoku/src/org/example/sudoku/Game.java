package org.example.sudoku;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Game extends Activity {
	
	int count2=0;
	
	int easy[]={3,6,0,0,0,0,0,0,0,0,0,4,2,3,0,8,0,0,0,0,0,0,0,4,2,0,0,
		      0,7,0,4,6,0,0,0,3,8,2,0,0,0,0,0,1,4,5,0,0,0,1,3,0,2,0,
		      0,0,1,9,0,0,0,0,0,0,0,7,0,4,8,3,0,0,0,0,0,0,0,0,0,4,5};
	
	int medium[]={6,5,0,0,0,0,0,7,0,0,0,0,5,0,6,0,0,0,0,1,4,0,0,0,0,0,5,
		      0,0,7,0,0,9,0,0,0,0,0,2,3,1,4,7,0,0,0,0,0,7,0,0,8,0,0,
		      5,0,0,0,0,0,6,3,0,0,0,0,2,0,1,0,0,0,0,3,0,0,0,0,0,9,7};
	
	int hard[]={ 0,0,9,0,0,0,0,0,0,0,8,0,6,0,5,0,2,0,5,0,1,0,7,8,0,0,0,
		      0,0,0,0,0,0,7,0,0,7,0,6,0,4,0,1,0,2,0,0,4,0,0,0,0,0,0,
		      0,0,0,7,2,0,9,0,3,0,9,0,3,0,1,0,8,0,0,0,0,0,0,0,6,0,0};
	
	int puzzle[];
	int temp[]={ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		      0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		      0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	private PuzzleView puzzleView;
	int count=0;
	
	//public static String parse_difficulty=null;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		int diff=getIntent().getExtras().getInt("difficulty");
		Log.i("oncreate","after diff");
	   puzzle= get_puzzle(diff);
	   for(int i=0;i<81;i++)
	   {
		   Log.i("temp_array", "pls");
		   if(puzzle[i]>0)
		   temp[i]=1;
		  
	   }
	   Log.i("oncreate","after getting puzzle");
	   
		calculate_used_tiles();
		Log.i("oncreate","after used tiles calculation");
		puzzleView = new PuzzleView(this);
	    setContentView(puzzleView);
	   // puzzleView.requestFocus();
	}
	
	int[] get_puzzle(int diff) {
        int[] puz={};
		if(diff==0)
			puz=easy;	
		if(diff==1)
			puz=medium;
		if(diff==2)
			puz=hard;
		return puz;
		
		
	}
	
	private int getTile(int x, int y) {
	      return puzzle[y * 9 + x];
	   }

	
	 private void setTile(int x, int y, int value) {
	      puzzle[y * 9 + x] = value;
	   }
	 
	 protected void setValidTile(int x, int y, int value) {
	      
	      setTile(x, y, value);
	      calculate_used_tiles();
	      
	   }


	
	String getTileString(int x,int y)
	{
	if(count==0)
	{
		Log.i("gettile_string", "before_get_tile");
		count++;
	}
	int n=getTile(x,y);
	if(n==0)
		return "";
	else
	{
		String str=Integer.toString(n);
		return str;
	}
	}
	
	 protected void showKeypadOrError(int x, int y) {
	      int tiles[] = getUsedTiles(x, y);
	      if (tiles.length == 2 && temp[y*9+x]==1 ) {
	    	  count=0;
		         Toast toast = Toast.makeText(this,
		               "Aaahan!!", Toast.LENGTH_SHORT);
		         toast.setGravity(Gravity.CENTER, 0, 0);
		         toast.show();
		      }
	      else
	      {
	      if (tiles.length == 9) {
	         Toast toast = Toast.makeText(this,
	               "No Moves", Toast.LENGTH_SHORT);
	         toast.setGravity(Gravity.CENTER, 0, 0);
	         toast.show();
	      } else {
	        // Log.d(TAG, "showKeypad: used=" + toPuzzleString(tiles));
	        Dialog v = new Keypad(this, tiles, puzzleView);
	        v.show();
	      }
	      }
	   }

	
	int used[][][]=new int[9][9][];
	
	protected int[] getUsedTiles(int x, int y) {
	      return used[x][y];
	   }
	
	int[] all_tiles_used()
	{
		int arr[]={1,1};
		return arr;
	}
	
	private void calculate_used_tiles() {
	      for (int x = 0; x < 9; x++) {
	         for (int y = 0; y < 9; y++) {
	        	 if(temp[y*9+x]==1)
	        	 {
	        	 used[x][y]=all_tiles_used();
	        //	 count=1;
	        	 }
	        	 else
	            used[x][y] = calculateUsedTiles(x, y);
	         }
	      }
	   }

	   private int[] calculateUsedTiles(int x, int y) {
	      int c[] = new int[9];
	      // horizontal
	      for (int i = 0; i < 9; i++) { 
	         if (i == y)
	            continue;
	         int t = getTile(x, i);
	         if (t != 0)
	            c[t - 1] = t;
	      }
	      // vertical
	      for (int i = 0; i < 9; i++) { 
	         if (i == x)
	            continue;
	         int t = getTile(i, y);
	         if (t != 0)
	            c[t - 1] = t;
	      }
	      // same cell block
	      int startx = (x / 3) * 3; 
	      int starty = (y / 3) * 3;
	      for (int i = startx; i < startx + 3; i++) {
	         for (int j = starty; j < starty + 3; j++) {
	            if (i == x && j == y)
	               continue;
	            int t = getTile(i, j);
	            if (t != 0)
	               c[t - 1] = t;
	         }
	      }
	      // compress
	      int nused = 0; 
	      for (int t : c) {
	         if (t != 0)
	            nused++;
	      }
	      int c1[] = new int[nused];
	      nused = 0;
	      for (int t : c) {
	         if (t != 0)
	            c1[nused++] = t;
	      }
	      return c1;
	   }
	
	
}

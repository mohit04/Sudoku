package org.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {

	

	private Game game;
	private float width; 
	private float height; 
	private int selX; 
	private int selY; 
	private final Rect selRect = new Rect();


	public PuzzleView(Context context) {
		super(context);
		this.game = (Game) context;
	//	setFocusable(true);
	//	setFocusableInTouchMode(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.i("on_size_changed", "width"+ w+" "+"height"+ h);
		Log.i("on_size_changed", "old_width"+ oldw+" "+"old_height"+ oldh);
		width = w / 9f;
		height = h / 9f;
		getRect(selX, selY, selRect);
		Log.d("sudoku", "onSizeChanged: width " + width + ", height " + height);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	private void select(int x, int y) {
		invalidate(selRect);
	//	selX = Math.min(Math.max(x, 0), 8);
		//selY = Math.min(Math.max(y, 0), 8);
		selX=x;
		selY=y;
		Log.i("select",Integer.toString(selX));
		Log.i("select",Integer.toString(selY));
		getRect(selX, selY, selRect);
		invalidate(selRect);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);
		int temp=(int)event.getX();
		int temp1=(int)event.getY();
		int temp2=(int)(event.getX()/width);
		int temp3=(int)(event.getY()/height);
		Log.i("on_touch_event",Integer.toString(temp));
		Log.i("on_touch_event",Integer.toString(temp1));
		Log.i("on_touch_event",Integer.toString(temp2));
		Log.i("on_touch_event",Integer.toString(temp3));
		select((int) (event.getX() / width), (int) (event.getY() / height));
		game.showKeypadOrError(selX, selY);
		//Log.d(TAG, "onTouchEvent: x " + selX + ", y " + selY);
		return true;
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height),
				(int) (x * width + width), (int) (y * height + height));
	}
 
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i("ondraw","before_draw_rect");
		Paint bg_color = new Paint();
		bg_color.setColor(getResources().getColor(R.color.puzzle_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), bg_color);
		
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));
		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.puzzle_light));
		
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, light);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
					hilite);
		}
		
		for (int i = 0; i < 9; i++) {
			if (i % 3 != 0)
				continue;
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
					hilite);
		}
		
		Paint fg = new Paint();
		fg.setColor(getResources().getColor(R.color.puzzle_foreground));
		fg.setTextSize(height * 0.75f);
		fg.setTextScaleX(width / height);
		fg.setTextAlign(Paint.Align.CENTER);
		fg.setStyle(Style.FILL);
	//	fg.setTextSize(height * 0.75f);
		FontMetrics fm = fg.getFontMetrics();
		Log.i("font metrics",fm.toString());
	//	Log.i("font metrics",fm.toString());
		float x = width / 2;
		float y = height / 2- (fm.ascent + fm.descent) / 2;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(this.game.getTileString(i, j), i*width +x , j
						* height+y , fg);
			}
		}
	
		Paint selected = new Paint();
		selected.setColor(getResources().getColor(R.color.puzzle_selected));
		canvas.drawRect(selRect, selected);
		
		if(prefrs.getHints(getContext()));
		{
		//	Log.i("hints",);
		Paint hint = new Paint();
		int c[] = { getResources().getColor(R.color.puzzle_hint_0),
				getResources().getColor(R.color.puzzle_hint_1),
				getResources().getColor(R.color.puzzle_hint_2), };
		Rect r = new Rect();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int movesleft = 9 - game.getUsedTiles(i, j).length;
				if (movesleft < c.length) {
					
					getRect(i, j, r);
					hint.setColor(c[movesleft]);
					canvas.drawRect(r, hint);
				}
			}
		}
		}
	}

	
	public void setSelectedTile(int tile) {
		game.setValidTile(selX, selY, tile);
			invalidate();
		
	}
	
	

}

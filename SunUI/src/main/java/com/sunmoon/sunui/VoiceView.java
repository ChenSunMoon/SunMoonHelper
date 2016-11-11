package com.sunmoon.sunui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VoiceView extends View {
	
	private float cR =0;
	private Paint paint;
	private int height;
	private int width;

	public VoiceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public VoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public VoiceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		height = getHeight();
		width = getWidth();
		paint.setColor(Color.LTGRAY);
		canvas.drawCircle((float)width / 2, (float)height/ 2, dip2px(getContext(), 30) + cR, paint);
		paint.setColor(Color.GRAY);


		canvas.drawCircle((float)width / 2, (float)height/ 2, dip2px(getContext(), 30), paint);
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		int r = dip2px(getContext(), 30);
		
		if ((x - width/2) * (x - width/2) + (y - height/2) * (y - height/2) <= r * r) {
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				if(onClickListener != null){
					onClickListener.onClick();
				}
			}
		}
		return super.onTouchEvent(event);
	}

	
	public static int dip2px(Context context, float dpValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
	}

	public void setVolume(float cR){
		this.cR = cR;
		this.invalidate();
	}
	
	public interface OnClickListener{
		void onClick();
	}
	
	private OnClickListener onClickListener = null;

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}


}

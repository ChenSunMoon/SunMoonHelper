package com.sunmoon.sunui.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class ImageUtils {
	private static final int COLORDRAWABLE_DIMENSION = 1;
	private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

	public static Bitmap bitmap2Drawable(Drawable mDrawable){
		if (mDrawable == null) {
			return null;
		}
		if (mDrawable instanceof BitmapDrawable) {
			return ((BitmapDrawable)mDrawable).getBitmap();
		}
		
		try {
			Bitmap mBitmap;
			if (mDrawable instanceof ColorDrawable) {
				mBitmap=Bitmap.createBitmap(COLORDRAWABLE_DIMENSION
						, COLORDRAWABLE_DIMENSION,BITMAP_CONFIG);
			}else {
				mBitmap=Bitmap.createBitmap(mDrawable.getIntrinsicWidth()
						,mDrawable.getIntrinsicHeight(),BITMAP_CONFIG);
			}
			Canvas canvas=new Canvas(mBitmap);
			mDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			mDrawable.draw(canvas);
			return mBitmap;
		} catch (OutOfMemoryError e) {
			// TODO ×ÔśŻÉúłÉľÄ catch żé
			e.printStackTrace();
			return null;
		}
	}
}
package com.sunmoon.sunui;

/**
 * Created by SunMoon on 2016/11/5.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.sunmoon.sunui.utils.ImageUtils;

import sunmon.com.sunui.R;

public class CircleImageView extends ImageView{

    private static final ScaleType DEFAULT_SCALE_TYPE= ScaleType.CENTER_CROP;//图片的显示方式
    private static final int DEFAULT_BORDER_WIDTH=0;
    private static final int DEFAULT_BORDER_COLOR=Color.TRANSPARENT;

    private int mBorderWidth=DEFAULT_BORDER_WIDTH;//轮廓的宽度-->默认0，默认颜色透明
    private int mBorderColor=DEFAULT_BORDER_COLOR;

    private final Matrix mShaderMatrix =new Matrix();
    private final Paint mBorderPaint=new Paint();
    private final Paint mBitmapPaint=new Paint();

    private final RectF mBitmapRect=new RectF();
    private final RectF mBorderRect=new RectF();

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mBitmapRadius;
    private float mBorderRadius;

    private boolean isReady;
    private boolean isSetupPending;



    public CircleImageView(Context context) {
        super(context,null,0);
    }
    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CircleImageView(Context context, AttributeSet attrs,
                           int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }
    //所有构造方法调用三参数的构造函数
    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setScaleType(DEFAULT_SCALE_TYPE);
        TypedArray mTypedArray=context.obtainStyledAttributes(attrs
                , R.styleable.CircleImageView, defStyleAttr, 0);
        mBorderWidth=mTypedArray.getDimensionPixelSize(R.styleable.CircleImageView_border_width
                , DEFAULT_BORDER_WIDTH);
        mBorderColor=mTypedArray.getColor(R.styleable.CircleImageView_border_color
                ,DEFAULT_BORDER_COLOR);
        mTypedArray.recycle();

        isReady=true;
        if (isSetupPending) {
            set();
            isSetupPending=false;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable()==null) {
            Log.e("onDraw-Function", "getDrawable()==null");
            return;
        }
        canvas.drawCircle(getWidth()/2, getHeight()/2, mBitmapRadius, mBitmapPaint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, mBorderRadius, mBorderPaint);
    }

    private void set() {

        if (!isReady) {
            isSetupPending=true;
            return;
        }
        if (mBitmap==null) {
            return;
        }

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setDither(true);
        mBorderPaint.setColor(DEFAULT_BORDER_COLOR);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(mBorderColor);

        mBitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP
                , Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBitmapWidth=mBitmap.getWidth();
        mBitmapHeight=mBitmap.getHeight();

        mBorderRect.set(0, 0, getWidth(), getHeight());
        mBorderRadius=(Math.min(mBorderRect.height(), mBorderRect.width())-mBorderWidth)*1.0f/2;
        mBitmapRect.set(mBorderWidth,mBorderWidth
                ,mBorderRect.width()-mBorderWidth,mBorderRect.height()-mBorderWidth);
        mBitmapRadius=Math.min(mBitmapRect.height(), mBitmapRect.width())/2;

        setShaderMatrix();
        invalidate();
    }

    private void setShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mBitmapRect.height() > mBitmapRect.width() * mBitmapHeight) {
            scale = mBitmapRect.height() / (float) mBitmapHeight;
            dx = (mBitmapRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mBitmapRect.width() / (float) mBitmapWidth;
            dy = (mBitmapRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }


    @Override
    public void setScaleType(ScaleType scaleType) {
        throw new IllegalArgumentException("CircleImageView强制要求ScaleType属性为CENTER_CROP");
    }
    @Override
    public ScaleType getScaleType() {
        return DEFAULT_SCALE_TYPE;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO 自动生成的方法存根
        super.onSizeChanged(w, h, oldw, oldh);
        set();
    }
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap= ImageUtils.bitmap2Drawable(getDrawable());
        set();
    }
    @Override
    public void setImageDrawable(Drawable drawable) {
        // TODO 自动生成的方法存根
        super.setImageDrawable(drawable);
        mBitmap=ImageUtils.bitmap2Drawable(drawable);
        set();
    }
    @Override
    public void setImageBitmap(Bitmap bm) {
        // TODO 自动生成的方法存根
        super.setImageBitmap(bm);
        mBitmap=bm;
        set();
    }


    /*
     * 自定义属性BorderColor、BorderWidth的set&get方法
     */
    public void setBorderColor(int mBorderColor){
        if (this.mBorderColor==mBorderColor) {
            return;
        }
        this.mBorderColor=mBorderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }
    public void setBorderWidth(int mBorderWidth) {
        if (this.mBorderWidth==mBorderWidth) {
            return;
        }
        this.mBorderWidth = mBorderWidth;
        set();
    }
    public int getBorderColor(){
        return mBorderColor;
    }
    public int getBorderWidth() {
        return mBorderWidth;
    }

}

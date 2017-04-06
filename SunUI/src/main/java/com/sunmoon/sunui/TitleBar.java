package com.sunmoon.sunui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunmoon.sunui.utils.DensityUtil;

import sunmon.com.sunui.R;


/**
 * 类描述：左导航栏
 * 创建人：ChenSunMoon
 * 创建时间：2015/9/25 11:36
 * 增加类容：将TextView修改成IconText增加向做的导航图标
 */
public class TitleBar extends ViewGroup {
    private static final int DEFAULT_MAIN_TEXT_SIZE = 19;
    private static final int DEFAULT_SUB_TEXT_SIZE = 19;
    private static final int DEFAULT_ACTION_TEXT_SIZE = 15;
    private static final int DEFAULT_TITLE_BAR_HEIGHT = 48;
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    private TextView mLeftText;
    private LinearLayout mRightLayout;
    private LinearLayout mCenterLayout;
    private TextView mCenterText;
    private TextView mSubTitleText;
    private View mDividerView;
    private boolean mImmersive;
    private int mScreenWidth;
    private int mStatusBarHeight;
    private int mOutPadding;
    private int mHeight;
    private int mLeftIcon;
    private String mTitle;
    private String mLeftTitle;
    private Context mContext;
    private boolean isShowLeft=false;
    LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    public TitleBar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttr(attrs);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.title);
        mTitle = a.getString(R.styleable.title_title);
        mLeftIcon = a.getResourceId(R.styleable.title_left_icon, R.drawable.ic_arrow_left);
        mLeftTitle = a.getString(R.styleable.title_left_text);
        isShowLeft=a.getBoolean(R.styleable.title_left_isShow,true);
        setImmersive(a.getBoolean(R.styleable.title_isImmersive,true));
        a.recycle();
    }

    private void init() {
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        }
        mOutPadding = DensityUtil.dip2px(mContext, 8);
        mHeight = DensityUtil.dip2px(mContext, DEFAULT_TITLE_BAR_HEIGHT);
        initView();
    }

    private void initView() {
        initLeftView();
        initCenterView();
        initRightView();
        initDivider();
    }

    private void initDivider() {
        mDividerView = new View(mContext);
        addView(mDividerView, new LayoutParams(LayoutParams.MATCH_PARENT, 1));
    }

    private void initRightView() {
        mRightLayout = new LinearLayout(mContext);
        mRightLayout.setPadding(mOutPadding, 0, mOutPadding, 0);
        addView(mRightLayout, layoutParams);
    }

    private void initLeftView() {
        mLeftText = new TextView(mContext);
        if (isShowLeft) {
            mLeftText.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
            mLeftText.setSingleLine();
            mLeftText.setGravity(Gravity.CENTER_VERTICAL);
            //mLeftText.setPadding(mOutPadding + mActionPadding, 0, mOutPadding, 0);
            mLeftText.setText(mLeftTitle);
            Drawable drawable = getResources().getDrawable(mLeftIcon);
            drawable.setBounds(0, 0, DensityUtil.dip2px(mContext, 38), DensityUtil.dip2px(mContext, 38));
            mLeftText.setTextColor(Color.WHITE);
            mLeftText.setCompoundDrawables(drawable, null, null, null);
            mLeftText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) mContext).setResult(404);
                    ((Activity) mContext).finish();
                }
            });
        }
        addView(mLeftText, layoutParams);
    }

    private void initCenterView() {
        mCenterLayout = new LinearLayout(mContext);
        mCenterText = new TextView(mContext);
        mSubTitleText = new TextView(mContext);
        mCenterLayout.addView(mCenterText);
        mCenterLayout.addView(mSubTitleText);
        mCenterLayout.setGravity(Gravity.CENTER);
        mCenterText.setTextSize(DEFAULT_MAIN_TEXT_SIZE);
        mCenterText.setSingleLine();
        mCenterText.setGravity(Gravity.CENTER);
        mCenterText.setTextColor(Color.WHITE);
        mCenterText.setEllipsize(TextUtils.TruncateAt.END);
        mCenterText.setText(mTitle);
        mSubTitleText.setTextSize(DEFAULT_SUB_TEXT_SIZE);
        mSubTitleText.setSingleLine();
        mSubTitleText.setGravity(Gravity.CENTER);
        mSubTitleText.setEllipsize(TextUtils.TruncateAt.END);
        mSubTitleText.setTextColor(Color.WHITE);
        addView(mCenterLayout);
    }

    //  是否开启了沉浸式
    public void setImmersive(boolean immersive) {
        mImmersive = immersive;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        } else {
            mStatusBarHeight = 0;
        }
    }

    public void setTitle(CharSequence title) {
        int index = title.toString().indexOf("\n");
        if (index > 0) {
            setTitle(title.subSequence(0, index), title.subSequence(index + 1, title.length()), LinearLayout.VERTICAL);
        } else {
            index = title.toString().indexOf("\t");
            if (index > 0) {
                setTitle(title.subSequence(0, index), "  " + title.subSequence(index + 1, title.length()), LinearLayout.HORIZONTAL);
            } else {
                mCenterText.setText(title);
                mSubTitleText.setVisibility(View.GONE);
            }
        }
    }

    private void setTitle(CharSequence title, CharSequence subTitle, int orientation) {
        mCenterLayout.setOrientation(orientation);
        mCenterText.setText(title);
        mSubTitleText.setText(subTitle);
        mSubTitleText.setVisibility(View.VISIBLE);
    }

    public void setCenterClickListener(OnClickListener l) {
        mCenterLayout.setOnClickListener(l);
    }

    public void setTitle(int resid) {
        setTitle(getResources().getString(resid));
    }

    public void setTitleColor(int resid) {
        mCenterText.setTextColor(resid);
    }

    public void setTitleSize(float size) {
        mCenterText.setTextSize(size);
    }

    public void setTitleBackground(int resid) {
        mCenterText.setBackgroundResource(resid);
    }

    public void setSubTitleColor(int resid) {
        mSubTitleText.setTextColor(resid);
    }

    public void setSubTitleSize(float size) {
        mSubTitleText.setTextSize(size);
    }


    public void setDivider(Drawable drawable) {
        mDividerView.setBackgroundDrawable(drawable);
    }

    public void setDividerColor(int color) {
        mDividerView.setBackgroundColor(color);
    }

    public void setDividerHeight(int dividerHeight) {
        mDividerView.getLayoutParams().height = dividerHeight;
    }


    /**
     * Function to set a click listener for Title TextView
     *
     * @param listener the onClickListener
     */
    public void setOnTitleClickListener(OnClickListener listener) {
        mCenterText.setOnClickListener(listener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;
        if (heightMode != MeasureSpec.EXACTLY) {
            height = mHeight + mStatusBarHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec) + mStatusBarHeight;
        }
        measureChild(mLeftText, widthMeasureSpec, heightMeasureSpec);
        measureChild(mRightLayout, widthMeasureSpec, heightMeasureSpec);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mLeftText.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        } else {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        }
        measureChild(mDividerView, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftText.layout(0, mStatusBarHeight, mLeftText.getMeasuredWidth(), mLeftText.getMeasuredHeight() + mStatusBarHeight);
        mRightLayout.layout(mScreenWidth - mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                mScreenWidth, mRightLayout.getMeasuredHeight() + mStatusBarHeight);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.layout(mLeftText.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mLeftText.getMeasuredWidth(), getMeasuredHeight());
        } else {
            mCenterLayout.layout(mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight());
        }
        mDividerView.layout(0, getMeasuredHeight() - mDividerView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }


    /**
     * 计算状态栏高度高度
     * getStatusBarHeight
     *
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setLeftClickListener(OnClickListener onClickListener) {
        mLeftText.setOnClickListener(onClickListener);
    }


}

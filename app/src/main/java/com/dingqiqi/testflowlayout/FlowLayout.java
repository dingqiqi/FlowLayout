package com.dingqiqi.testflowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流式布局
 * Created by dingqiqi on 2016/12/15.
 */
public class FlowLayout extends ViewGroup {

    private int mWidth, mHeight;
    private int mMarginWidth;
    private int mMarginHeight;
    private Context mContext;

    public FlowLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        mMarginHeight = dp2px(5);
        mMarginWidth = dp2px(8);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = 0;
        mHeight = 0;

        for (int j = 0; j < getChildCount(); j++) {
            View child = getChildAt(j);

            if (child.getVisibility() == View.GONE) {
                continue;
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            int width = child.getMeasuredWidth() + child.getPaddingLeft() + child.getPaddingRight();
            int height = child.getMeasuredHeight() + child.getPaddingTop() + child.getPaddingBottom();

            if (mWidth + width + mMarginWidth > getMeasuredWidth()) {
                mWidth = width;
                mHeight = mHeight + height + mMarginHeight;
            } else {
                mWidth = mWidth + width + mMarginWidth;
                mHeight = Math.max(mHeight, height);
            }
        }

        setMeasuredDimension(getDefaultSpec(widthMeasureSpec, 0), getDefaultSpec(heightMeasureSpec, 1));
    }

    private int getDefaultSpec(int spec, int flag) {
        int value;
        if (flag == 0) {
            value = getMeasuredWidth() + getPaddingLeft() + getPaddingRight();
        } else {
            value = mHeight != 0 ? mHeight : getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
        }

        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);

        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        }

        return value;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mWidth = 0;
        mHeight = 0;

        for (int j = 0; j < getChildCount(); j++) {
            View child = getChildAt(j);

            if (child.getVisibility() == View.GONE) {
                continue;
            }

            int width = child.getMeasuredWidth() + child.getPaddingLeft() + child.getPaddingRight();
            int height = child.getMeasuredHeight() + child.getPaddingTop() + child.getPaddingBottom();

            int left, top;
            if (mWidth + width + mMarginWidth > getMeasuredWidth()) {
                mWidth = width;
                mHeight = mHeight + height + mMarginHeight;
            } else {
                if (j == 0) {
                    mWidth = mWidth + width;
                } else {
                    mWidth = mWidth + width + mMarginWidth;
                }
                mHeight = Math.max(mHeight, height);
            }

            left = Math.abs(mWidth - width);
            top = Math.abs(mHeight - height);

            child.layout(left, top, left + width, top + height);
        }
    }

    public int dp2px(int dp) {
        return (int) (mContext.getResources().getDisplayMetrics().density * dp + 0.5);
    }

}

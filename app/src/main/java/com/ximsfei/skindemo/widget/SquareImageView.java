package com.ximsfei.skindemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by pengfengwang on 2017/1/16.
 */

public class SquareImageView extends ImageView {
    private static final float DEFAULT_ASPECT_RATIO = 1.0f;
    private float mAspectRatio = DEFAULT_ASPECT_RATIO;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mAspectRatio <= 0) {
            mAspectRatio = DEFAULT_ASPECT_RATIO;
        }
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() * mAspectRatio));
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
    }
}

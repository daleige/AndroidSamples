package com.wangyi.musicplayer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class UIRelativeLayout  extends RelativeLayout {
    private boolean flag=true;
    public UIRelativeLayout(Context context) {
        super(context);
    }

    public UIRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(flag) {
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scaleY = UIUtils.getInstance(getContext()).getVerticalScaleValue();
            int childCount = this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = this.getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
            flag=false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

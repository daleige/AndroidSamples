package com.cyq.expendtextview.test;

import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Time: 2019-12-22 22:44
 * Author: ChenYangQi
 * Description:
 */
public class OverLinkMovementMethod extends LinkMovementMethod {

    public static boolean canScroll = false;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_MOVE) {
            if (!canScroll) {
                return true;
            }
        }

        return super.onTouchEvent(widget, buffer, event);
    }

    public static MovementMethod getInstance() {
        if (sInstance == null)
            sInstance = new OverLinkMovementMethod();

        return sInstance;
    }

    private static OverLinkMovementMethod sInstance;
    private static Object FROM_BELOW = new NoCopySpan.Concrete();
}
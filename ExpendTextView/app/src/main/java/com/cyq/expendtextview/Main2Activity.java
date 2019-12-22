package com.cyq.expendtextview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.expendtextview.test.ExpandableTextView;

public class Main2Activity extends AppCompatActivity {
    ExpandableTextView expandableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        expandableTextView = findViewById(R.id.expanded_text);
        int viewWidth = getWindowManager().getDefaultDisplay().getWidth() - dp2px(this, 20f);
        expandableTextView.initWidth(viewWidth);
        expandableTextView.setMaxLines(3);
        expandableTextView.setHasAnimation(true);
        expandableTextView.setCloseInNewLine(true);
        expandableTextView.setOpenSuffixColor(getResources().getColor(R.color.colorAccent));
        expandableTextView.setCloseSuffixColor(getResources().getColor(R.color.colorAccent));
        expandableTextView.setOriginalText(getResources().getString(R.string.text_content));
        expandableTextView.setOpenAndCloseCallback(new ExpandableTextView.OpenAndCloseCallback() {
            @Override
            public void onOpen() {
                Log.i("test","open");
            }

            @Override
            public void onClose() {
                Log.i("test","close");
            }
        });
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        int res;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0)
            res = -(int) (-dpValue * scale + 0.5f);
        else
            res = (int) (dpValue * scale + 0.5f);
        return res;
    }

    public void tagger(View view) {
        Log.i("test","click");

        expandableTextView.switchOpenClose();
    }
}

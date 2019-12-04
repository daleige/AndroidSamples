package com.cyq.tbs.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * @author : ChenYangQi
 * date   : 2019/12/4 14:11
 * desc   : 基于TbsReaderView封装一层，便于直接在XML中使用
 */
public class FileReaderView extends FrameLayout {
    private static final String TEMP_PATH = "/TbsTempPath";
    private TbsReaderView mTbsReaderView;

    public FileReaderView(@NonNull Context context) {
        this(context, null);
    }

    public FileReaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FileReaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTbsReaderView = new TbsReaderView(getContext(), new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        addView(mTbsReaderView);
    }

    /**
     * 展示office文档
     *
     * @param localPath 要展示的文档的本地路径
     */
    public void openFile(String localPath) {
        try {
            String bsReaderTemp = Environment.getExternalStorageDirectory() + TEMP_PATH;
            File bsReaderTempFile = new File(bsReaderTemp);
            if (!bsReaderTempFile.exists()) {
                bsReaderTempFile.mkdir();
            }
            Bundle bundle = new Bundle();
            bundle.putString("filePath", localPath);
            bundle.putString("tempPath", bsReaderTemp);
            boolean isSupportType = mTbsReaderView.preOpen(getFileType(localPath), false);
            if (isSupportType) {
                mTbsReaderView.openFile(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源，如不调用onStop，下一次打开文件，将显示上一次的文件内容
     */
    public void destroy() {
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }

    /***
     * 获取文件类型
     *
     * @param path 文件路径
     * @return 文件的格式
     */
    private String getFileType(String path) {
        String str = "";

        if (TextUtils.isEmpty(path)) {
            return str;
        }
        int i = path.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }
        str = path.substring(i + 1);
        return str;
    }
}

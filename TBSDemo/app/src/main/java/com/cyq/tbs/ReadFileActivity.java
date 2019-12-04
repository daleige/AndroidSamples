package com.cyq.tbs;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * @author ChenYangQi
 */
public class ReadFileActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {
    private FrameLayout mFl;
    private TbsReaderView mTbsReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        mFl = findViewById(R.id.fl_file);
        mTbsReaderView = new TbsReaderView(this, this);
        mFl.addView(mTbsReaderView);
        initReadFile();
    }

    private void initReadFile() {
        String bsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsTempPath";
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            bsReaderTempFile.mkdir();
        }
        String path = "/sdcard/安卓开发规范.pdf";
        //通过bundle把文件传给x5,打开的事情交由x5处理
        Bundle bundle = new Bundle();
        bundle.putString("filePath", path);
        bundle.putString("tempPath", bsReaderTemp);
        //加载文件前的初始化工作,加载支持不同格式的插件
        boolean b = mTbsReaderView.preOpen(getFileType(path), false);
        if (b) {
            mTbsReaderView.openFile(bundle);
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

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

}

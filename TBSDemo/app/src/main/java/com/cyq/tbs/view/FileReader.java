package com.cyq.tbs.view;

import android.content.Context;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

/**
 * @author : ChenYangQi
 * date   : 2019/12/4 14:55
 * desc   : office文件浏览组建初始化，实际上也就是TBS的初始化
 */
public class FileReader {
    public static void init(Context applicationContext) {
        /**
         * 非wifi条件下允许下载X5内核
         */
        QbSdk.setDownloadWithoutWifi(true);

        /**
         * 搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
         */
        QbSdk.PreInitCallback callback = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg) {
                //x5內核初始化完成的回调，
                // true表示x5内核加载成功，
                // false表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
            }

            @Override
            public void onInstallFinish(int i) {
            }

            @Override
            public void onDownloadProgress(int i) {
            }
        });
        QbSdk.initX5Environment(applicationContext, callback);
    }

}

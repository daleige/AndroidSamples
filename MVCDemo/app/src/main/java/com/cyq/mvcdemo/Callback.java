package com.cyq.mvcdemo;

import com.cyq.mvcdemo.bean.ImageBean;

public interface Callback {

    /**
     * @param resultCode 请求结果返回的标识码
     * @param bitmap     Model层数据中Bitmap对象，用户Control刷新View
     */
    void callback(int resultCode, ImageBean bitmap);

}

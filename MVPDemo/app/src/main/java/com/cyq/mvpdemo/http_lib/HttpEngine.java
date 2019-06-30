package com.cyq.mvpdemo.http_lib;

import com.cyq.mvpdemo.bean.UserInfo;
import com.cyq.mvpdemo.login.LoginPresenter;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public class HttpEngine<P extends LoginPresenter> {
    private P p;

    public HttpEngine(P p) {
        this.p = p;
    }

    public void post(String name, String pwd) {
        if ("zhangsan".equalsIgnoreCase(name) && "123456".equalsIgnoreCase(pwd)) {
            p.getContract().responseResult(new UserInfo("腾讯", "马化腾"));
        } else {
            p.getContract().responseResult(null);
        }
    }
}

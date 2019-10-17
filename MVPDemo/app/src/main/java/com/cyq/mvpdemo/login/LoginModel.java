package com.cyq.mvpdemo.login;

import com.cyq.mvpdemo.base.BaseModel;
import com.cyq.mvpdemo.bean.UserInfo;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                //模拟网络请求
                if ("zhangsan".equalsIgnoreCase(name) && "123456".equalsIgnoreCase(pwd)) {
                    p.getContract().responseResult(new UserInfo("腾讯", "马化腾"));
                } else {
                    p.getContract().responseResult(null);
                }
            }

            @Override
            public void exexteLogin(String str) {

            }
        };
    }
}

package com.cyq.mvpdemo.login;

import com.cyq.mvpdemo.LoginActivity;
import com.cyq.mvpdemo.base.BasePresenter;
import com.cyq.mvpdemo.bean.UserInfo;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel,
        LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        //既要履行View给他的需求，又要分配工作给Model去完成这个需求
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {
                //三种风格
                try {
                    m.getContract().executeLogin(name,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                //不管谁完成
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}

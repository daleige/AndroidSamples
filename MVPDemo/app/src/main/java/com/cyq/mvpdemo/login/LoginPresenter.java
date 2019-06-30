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
                //P层三种风格
                try {
                    //第一种：P层不做事情，只做转发，交给M层做
                    m.getContract().executeLogin(name, pwd);

                    //第二种：让功能模块做(Library:下载，请求，图片加载等功能模块)
                    //HttpEngine httpEngine = new HttpEngine<>(LoginPresenter.this);
                    //httpEngine.post(name, pwd);

                    //第三种：P层自己处理(google MVP samples就是这种)
                    /*if ("zhangsan".equalsIgnoreCase(name) && "123456".equalsIgnoreCase(pwd)) {
                        responseResult(new UserInfo("腾讯", "马化腾"));
                    } else {
                        responseResult(null);
                    }*/

                    //内存泄漏测试
                    /*new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(60000);
                        }
                    }).start();*/
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

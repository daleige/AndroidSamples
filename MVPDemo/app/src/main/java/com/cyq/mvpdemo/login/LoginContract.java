package com.cyq.mvpdemo.login;

import com.cyq.mvpdemo.bean.BaseEntity;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:将Model层，Presenter层协商的共同业务，封装成接口、契约、合同
 */
public interface LoginContract {
    interface Model {
        void executeLogin(String name, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity> {
        //真实项目中们，请求结果往往是JavaBean
        Void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        //登录请求(接收到View层的指令，可以自己做，也可以让Model层去执行)
        void requestLogin(String name, String pwd);

        //结果响应（接收到Model层处理的结果，通知View层刷新）
        void responseResult(T t);
    }
}

package com.cyq.mvvmdemo.vm;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.cyq.mvvmdemo.databinding.ActivityMainBinding;
import com.cyq.mvvmdemo.model.UserInfo;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public class LoginViewModel {

    public UserInfo userInfo;

    public LoginViewModel(ActivityMainBinding binding) {
        userInfo = new UserInfo();
        //将ViewModel和View进行绑定，通过DataBinding工具
        binding.setLoginViewModel(this);
    }

    public TextWatcher nameInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //View层接收到用户的输入，改变javaBean的属性
            userInfo.name.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher pwdInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userInfo.pwd.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //Model层属性变更，改变View层显示
                    SystemClock.sleep(2000);
                    if ("zhangsan".equalsIgnoreCase(userInfo.name.get()) && "123456".equalsIgnoreCase(userInfo.pwd.get())) {
                        Log.i("test", "登录成功");
                    } else {
                        Log.i("test", "登录失败");

                    }
                }
            }).start();
        }
    };

//    Handler handler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    Toast.makeText(???, "登录成功~", Toast.LENGTH_SHORT).show();
//                    break;
//                case 2:
//
//                    break;
//            }
//            return false;
//        }
//    });

}

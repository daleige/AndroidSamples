package com.cyq.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cyq.mvpdemo.base.BaseView;
import com.cyq.mvpdemo.bean.UserInfo;
import com.cyq.mvpdemo.login.LoginContract;
import com.cyq.mvpdemo.login.LoginPresenter;

public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {
    private EditText nameEt;
    private EditText pwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        nameEt = findViewById(R.id.et_account);
        pwdEt = findViewById(R.id.et_password);
    }

    public void doLoginAction(View view) {
        String name = nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        p.getContract().requestLogin(name, pwd);
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {
                if (userInfo != null) {
                    Toast.makeText(LoginActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getDataResult(UserInfo userInfo) {

            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}

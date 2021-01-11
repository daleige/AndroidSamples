package com.example.token_demo.entity;

/**
 * @author:
 * @create: 2019-10-16 09:59
 **/
public class User {
    public String account;
    public String password;
    public String lastLoginTime;

    public User(){

    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
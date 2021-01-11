package com.example.token_demo.controller;

import com.example.token_demo.entity.Result;
import com.example.token_demo.entity.User;
import com.example.token_demo.redis.RedisUtil;
import com.example.token_demo.service.AuthorizationService;
import com.google.gson.Gson;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author:
 * @create: 2019-10-16 10:02
 **/
@RestController
public class AccountController {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    RedisUtil cache;

    private final String jsonStr = "{\"code\":200,\"desc\":\"success\",\"data\":{}}";

    /**
     * 注册
     *
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestParam(name = "account") String account,
                           @RequestParam(name = "password") String password) {
        Result<String> result = new Result<>();
        //简单校验下非空
        if (account == null || account.equals("") || password == null || password.equals("")) {
            result.setCode(201);
            result.setDescription("账号或者密码为空");
            return new Gson().toJson(result);
        }

        //注册成功，这里没有判断要注册的用户是否存在
        //...

        //写入数据库成功后缓存(数据库操作这里就忽略了)
        cache.set("user" + account, new User(account, password));

        //response
        result.setCode(200);
        result.setDescription("success");
        result.setData("注册成功！");
        return new Gson().toJson(result);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam(name = "account") String account,
                        @RequestParam(name = "password") String password) {
        JSONObject ret = new JSONObject();
        User user = cache.get("user" + account);
        //如果缓存account获取user为null,从数据库获取user
        //...
        Result<JSONObject> result = new Result<>();
        if (user != null && password != null && password.equals(user.getPassword())) {
            //登录成功
            //创建token
            String accessToken = authorizationService.createAccessIdToken(account);
            String refreshToken = authorizationService.createRefreshIdToken(account);
            if (accessToken == null || refreshToken == null) {
                result.setCode(201);
                result.setDescription("账号或者密码为空");
                return new Gson().toJson(result);
            }

            //缓存当前登录用户 refreshToken 创建的起始时间，这个会在刷新accessToken方法中 判断是否要重新生成(刷新)refreshToken时用到
            cache.set("id_refreshTokenStartTime" + account, System.currentTimeMillis(), (int) AuthorizationService.refreshTokenExpirationTime);

            //更新用户最近登录时间
            user.setLastLoginTime(new Date().toLocaleString());
            //更新user到数据库成功后缓存(数据库操作这里就忽略了)
            cache.set("user" + account, user);

            //response
            result.setCode(200);
            result.setDescription("success");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessToken", accessToken);
            jsonObject.put("refreshToken", refreshToken);
            result.setData(jsonObject);
            return new Gson().toJson(result);
        }
        result.setCode(202);
        result.setDescription("执行失败！");
        return new Gson().toJson(result);
    }

    /**
     * 用 refreshToken 来刷新 accessToken
     *
     * @param refreshToken refreshToken
     * @return
     */
    @PostMapping("/accessToken/refresh")
    public ResponseEntity<String> accessTokenRefresh(@RequestHeader(name = "refreshToken") String refreshToken) {
        //刷新accessToken:生成新的accessToken
        Result<JSONObject> result = new Result<>();
        String account = authorizationService.verifyToken(refreshToken);
        JSONObject ret = new JSONObject();
        if (account == null) {
            //通过返回码 告诉客户端 refreshToken过期了，需要客户端就得跳转登录界面
            result.setCode(401);
            result.setDescription("token过期");
            return ResponseEntity.status(401).body(new Gson().toJson(result));
        }
        //创建新的accessToken
        String accessToken = authorizationService.createAccessIdToken(account);

        //下面判断是否刷新 refreshToken，如果refreshToken 快过期了 需要重新生成一个替换掉
        long minTimeOfRefreshToken = 2 * AuthorizationService.accessTokenExpirationTime;//refreshToken 有效时长是应该为accessToken有效时长的2倍 (我在博文里有介绍)
        Long refreshTokenStartTime = cache.get("id_refreshTokenStartTime" + account);//refreshToken创建的起始时间点
        //(refreshToken上次创建的时间点 + refreshToken的有效时长 - 当前时间点) 表示refreshToken还剩余的有效时长，如果小于2倍accessToken时长 ，则刷新 refreshToken
        if (refreshTokenStartTime == null || (refreshTokenStartTime + AuthorizationService.refreshTokenExpirationTime * 1000) - System.currentTimeMillis() <= minTimeOfRefreshToken * 1000) {
            //刷新refreshToken
            refreshToken = authorizationService.createRefreshIdToken(account);
            cache.set("id_refreshTokenStartTime" + account, System.currentTimeMillis(), (int) AuthorizationService.refreshTokenExpirationTime);
        }

        //response
        result.setCode(200);
        result.setDescription("success");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("refreshToken", refreshToken);
        result.setData(jsonObject);
        return ResponseEntity.status(200).body(new Gson().toJson(result));
    }

    /**
     * 查询用户信息
     *
     * @param accessToken accessToken
     * @return
     */
    @PostMapping("/userinfo")
    public ResponseEntity<String> userInfo(@RequestHeader(name = "accessToken") String accessToken) {
        System.out.println("调用获取用户信息接口");
        Result<User> result = new Result<>();
        String account = authorizationService.verifyToken(accessToken);
        System.out.println("account=" + account);
        if (account == null) {
            //通过返回码 告诉客户端 accessToken过期了，需要调用刷新accessToken的接口
            result.setCode(401);
            result.setDescription("accessToken expire");
            return ResponseEntity.status(401).body(new Gson().toJson(result));
        }

        User user = cache.get("user" + account);
        //如果缓存的user为null 从数据库查询...
        //...

        //response
        result.setCode(200);
        result.setDescription("success");
        result.setData(user);
        return ResponseEntity.status(200).body(new Gson().toJson(result));
    }
}

package com.example.token_demo.controller;

import com.example.token_demo.entity.User;
import com.example.token_demo.redis.RedisUtil;
import com.example.token_demo.service.AuthorizationService;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 注册
     * @param account
     * @param password
     * @return
     */
    @GetMapping("/register")
    public String register(@RequestParam(name = "account") String account,
                           @RequestParam(name = "password") String password){
        JSONObject ret = new JSONObject();
        //简单校验下非空
        if(account == null || account.equals("") || password == null || password.equals("")){
            ret.put("code","1");
            ret.put("desc","account | password can not empty");
            return ret.toString();
        }

        //注册成功，这里没有判断要注册的用户是否存在
        //...

        //写入数据库成功后缓存(数据库操作这里就忽略了)
        cache.set("user"+account,new User(account,password));

        //response
        ret.put("code","0");
        ret.put("desc","ok");
        return ret.toString();
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam(name = "account") String account,
                        @RequestParam(name = "password") String password){
        JSONObject ret = new JSONObject();
        User user = cache.get("user"+account);
        //如果缓存account获取user为null,从数据库获取user
        //...

        if(user != null && password != null && password.equals(user.getPassword())){
            //登录成功
            //创建token
            String accessToken = authorizationService.createAccessIdToken(account);
            String refreshToken = authorizationService.createRefreshIdToken(account);
            if(accessToken == null || refreshToken == null){
                ret.put("code","1");
                ret.put("desc","failed");
                return ret.toString();
            }

            //缓存当前登录用户 refreshToken 创建的起始时间，这个会在刷新accessToken方法中 判断是否要重新生成(刷新)refreshToken时用到
            cache.set("id_refreshTokenStartTime"+account,System.currentTimeMillis(),(int)AuthorizationService.refreshTokenExpirationTime);

            //更新用户最近登录时间
            user.setLastLoginTime(new Date().toLocaleString());
            //更新user到数据库成功后缓存(数据库操作这里就忽略了)
            cache.set("user"+account,user);

            //response
            ret.put("code","0");
            ret.put("desc","ok");
            ret.put("accessToken",accessToken);
            ret.put("refreshToken",refreshToken);
            return ret.toString();
        }
        ret.put("code","1");
        ret.put("desc","failed");
        return ret.toString();
    }

    /**
     * 用 refreshToken 来刷新 accessToken
     * @param refreshToken refreshToken
     * @return
     */
    @GetMapping("/accessToken/refresh/{refreshToken}")
    public String accessTokenRefresh(@PathVariable("refreshToken") String refreshToken){
        //刷新accessToken:生成新的accessToken
        String account = authorizationService.verifyToken(refreshToken);
        JSONObject ret = new JSONObject();
        if(account == null){
            //通过返回码 告诉客户端 refreshToken过期了，需要客户端就得跳转登录界面
            ret.put("code","3");//我这里只是演示，返回3表示 refreshToken过期
            ret.put("desc","failed");
            return ret.toString();
        }
        //创建新的accessToken
        String accessToken = authorizationService.createAccessIdToken(account);

        //下面判断是否刷新 refreshToken，如果refreshToken 快过期了 需要重新生成一个替换掉
        long minTimeOfRefreshToken = 2*AuthorizationService.accessTokenExpirationTime;//refreshToken 有效时长是应该为accessToken有效时长的2倍 (我在博文里有介绍)
        Long refreshTokenStartTime = cache.get("id_refreshTokenStartTime"+account);//refreshToken创建的起始时间点
        //(refreshToken上次创建的时间点 + refreshToken的有效时长 - 当前时间点) 表示refreshToken还剩余的有效时长，如果小于2倍accessToken时长 ，则刷新 refreshToken
        if(refreshTokenStartTime == null || (refreshTokenStartTime + AuthorizationService.refreshTokenExpirationTime*1000) - System.currentTimeMillis() <= minTimeOfRefreshToken*1000){
            //刷新refreshToken
            refreshToken = authorizationService.createRefreshIdToken(account);
            cache.set("id_refreshTokenStartTime"+account,System.currentTimeMillis(),(int)AuthorizationService.refreshTokenExpirationTime);
        }

        //response
        ret.put("code","0");
        ret.put("desc","ok");
        ret.put("accessToken",accessToken);
        ret.put("refreshToken",refreshToken);
        return ret.toString();
    }

    /**
     * 查询用户信息
     * @param accessToken accessToken
     * @return
     */
    @GetMapping("/user/{accessToken}")
    public String userInfo(@PathVariable("accessToken") String accessToken){
        String account = authorizationService.verifyToken(accessToken);
        System.out.println("account="+account);
        JSONObject ret = new JSONObject();
        if(account == null){
            //通过返回码 告诉客户端 accessToken过期了，需要调用刷新accessToken的接口
            ret.put("code","2");//我这里只是演示，返回2表示 accessToken过期
            ret.put("desc","accessToken expire");
            return ret.toString();
        }

        User user = cache.get("user"+account);
        //如果缓存的user为null 从数据库查询...
        //...

        //response
        ret.put("code","0");
        ret.put("desc","ok");
        ret.put("user",user);
        return ret.toJSONString();
    }
}

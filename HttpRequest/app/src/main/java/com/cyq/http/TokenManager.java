package com.cyq.http;

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 17:58
 * desc   : 单例 用于存储accessToken和refreshToken
 */
public class TokenManager {
    private static volatile TokenManager instance;
    private String accessToken;
    private String refreshToken;

    private TokenManager() {
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (TokenManager.class) {
                if (instance == null) {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

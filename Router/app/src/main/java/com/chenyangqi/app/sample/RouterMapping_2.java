package com.chenyangqi.app.sample;

import java.util.HashMap;
import java.util.Map;

public class RouterMapping_2 {

    public static Map<String, String> get() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("router://page_login", "com.chenyangqi.business.LoginActivity");
        return mapping;
    }
}

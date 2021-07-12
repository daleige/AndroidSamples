package com.chenyangqi.app.sample;

import java.util.HashMap;
import java.util.Map;

public class RouterMapping_1 {

    public static Map<String, String> get() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("router://home_page", "com.chenyangqi.app.MainActivity");
        mapping.put("router://page_kt", "com.chenyangqi.app.KtMainActivity");
        return mapping;
    }
}

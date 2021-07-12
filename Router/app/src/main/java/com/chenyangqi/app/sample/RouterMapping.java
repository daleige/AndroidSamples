package com.chenyangqi.app.sample;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ChenYangQi
 * date   : 7/12/21 23:54
 * desc   :
 */
public class RouterMapping {

    public static Map<String, String> get() {
        Map<String, String> map = new HashMap<>();
        map.putAll(RouterMapping_1.get());
        map.putAll(RouterMapping_2.get());
        return map;
    }

}

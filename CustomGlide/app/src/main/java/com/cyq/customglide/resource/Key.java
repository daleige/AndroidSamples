package com.cyq.customglide.resource;

import com.cyq.customglide.Tool;

/**
 * Time: 2019-09-21 14:54
 * Author: ChenYangQi
 * Description:资源的唯一描述
 */
public class Key {

    private String key;

    public Key(String key) {
        key = Tool.getSHA256StrJava(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

package com.cyq.lib_statelayout;

/**
 * @author : ChenYangQi
 * date   : 2019/12/7 8:03
 * desc   : 定义几种状态的标志
 */
public class StateType {
    /**
     * 加载中状态
     */
    public static final int LOADING_STATE = 0x001;
    /**
     * 错误状态
     */
    public static final int ERROR_STATE = 0x002;
    /**
     * 内容状态
     */
    public static final int CONTENT_STATE = 0x003;
    /**
     * 自定义内容状态
     */
    public static final int CUSTOM_STATE = 0x004;
    /**
     * 数据为空状态
     */
    public static final int EMPTY_STATE = 0x005;
}

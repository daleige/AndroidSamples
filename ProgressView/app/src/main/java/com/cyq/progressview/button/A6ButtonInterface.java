package com.cyq.progressview.button;

/**
 * @author : ChenYangQi
 * date   : 2020/6/3 16:02
 */
public interface A6ButtonInterface {

    /**
     * 设置按钮高亮的颜色  YELLOW BLUE GREEN
     *
     * @param colorType
     */
    void setColorType(ButtonType colorType);

    /**
     * 设置按钮的类型 高亮=light_button 灰色=normal_button  图标按钮=action_button
     *
     * @param btnType
     */
    void setBtnType(ButtonType btnType);

    /**
     * 设置按钮图标类型 打钩=positive 打叉=negative
     *
     * @param actionType
     */
    void setActionType(ButtonType actionType);

    /**
     * 设置按钮是否有点击动效
     *
     * @param enable
     */
    void setMyTouchEnable(boolean enable);

//    /**
//     * 设置文本
//     * @param str
//     */
//    void setText(String str);
}

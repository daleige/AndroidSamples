package com.cyq.ui.dialog.builder;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 14:44
 * desc   : 各种类型的 Builder都应该继承DialogBuilder，该类定义一些通用的方法和规范
 */
public abstract class DialogBuilder {

    /**
     * 设置标题
     */
    public abstract DialogBuilder setTitle(String title);

    /**
     * 设置dialog内容
     *
     * @param message
     */
    public abstract DialogBuilder setMessage(String message);

    /**
     * 点击外部是否关闭dialog
     *
     * @param b
     */
    public abstract DialogBuilder setCanceledOnTouchOutside(boolean b);

    /**
     * 生成一个dialog，但不显示
     *
     * @return
     */
    public abstract DialogBuilder create();

    /**
     * 根据style生成dialog，但不显示
     *
     * @param styleId
     * @return
     */
    public abstract DialogBuilder create(int styleId);

    /**
     * 生成一个Dialog，并显示
     */
    public abstract void show();
}

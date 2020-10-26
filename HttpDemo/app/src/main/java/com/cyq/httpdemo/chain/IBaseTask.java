package com.cyq.httpdemo.chain;

/**
 * @author : ChenYangQi
 * date   : 2020/10/26 15:47
 * desc   :
 */
interface IBaseTask {
    /**
     * @param task      事务内容
     * @param iBaseTask 下一个任务节点
     */
    public void doAction(String content, IBaseTask iBaseTask);
}

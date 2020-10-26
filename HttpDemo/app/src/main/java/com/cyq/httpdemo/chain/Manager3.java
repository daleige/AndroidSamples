package com.cyq.httpdemo.chain;

/**
 * @author : ChenYangQi
 * date   : 2020/10/26 15:53
 * desc   :
 */
class Manager3 implements IBaseTask {
    @Override
    public void doAction(String content, IBaseTask iBaseTask) {
        if (content.equals("请假")) {
            System.out.println("Manager3 同意请假 流程结束");
            return;
        }
    }
}

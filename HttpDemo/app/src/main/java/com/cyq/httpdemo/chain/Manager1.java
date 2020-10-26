package com.cyq.httpdemo.chain;

/**
 * @author : ChenYangQi
 * date   : 2020/10/26 15:52
 * desc   :
 */
class Manager1 implements IBaseTask {

    @Override
    public void doAction(String content, IBaseTask iBaseTask) {
        if (content.equals("请假")) {
            System.out.println("Manager1 同意请假 并转交给下一个Manager审批...");
            iBaseTask.doAction(content, iBaseTask);
        } else {

        }
    }
}

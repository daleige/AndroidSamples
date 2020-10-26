package com.cyq.httpdemo.chain;

/**
 * @author : ChenYangQi
 * date   : 2020/10/26 16:05
 * desc   :
 */
public class Test {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Manager1());
        taskManager.addTask(new Manager2());
        taskManager.addTask(new Manager3());
        taskManager.doAction("请假", taskManager);
    }
}

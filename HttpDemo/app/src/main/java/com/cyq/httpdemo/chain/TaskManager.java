package com.cyq.httpdemo.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2020/10/26 15:54
 * desc   :
 */
class TaskManager implements IBaseTask {
    private List<IBaseTask> taskList;
    private int index = 0;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(IBaseTask task) {
        if (taskList == null || taskList.isEmpty()) {
            this.taskList = new ArrayList<>();
        }
        taskList.add(task);
    }

    @Override
    public void doAction(String content, IBaseTask iBaseTask) {
        if (taskList.isEmpty()) {
            System.out.println("taskList is empty!");
            return;
        }
        if (index < 0 || index >= taskList.size()) {
            System.out.println("index out of bundle!");
            return;
        }
        IBaseTask task = taskList.get(index);
        index++;
        task.doAction(content, iBaseTask);
    }
}

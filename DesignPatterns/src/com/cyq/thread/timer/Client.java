package src.com.cyq.thread.timer;

import java.util.*;

public class Client {
    public static void main(String[] args) {
//        Timer timer = new Timer();
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("timerTasK running");
//            }
//        };
//
//        System.out.println("begin ---");
//        timer.schedule(task,2000);

        long time = System.currentTimeMillis();
        System.out.println("开始时间：" + time);

        long runTime = time + 5_000;
        System.out.println("预计执行时间：" + runTime);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("实际执行时间：" + System.currentTimeMillis());
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, new Date(runTime));

        Map<String, String> map = new HashMap<>();
        map.put("111", "张三");
        System.out.println("------------" + map.get("111"));
        map.put("111", "李四");
        System.out.println("************" + map.get("111"));
    }
}

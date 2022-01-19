package src.com.cyq.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

//        TestTask mTask = new TestTask();
//        mTask.setCallback(new TestTask.Callback() {
//            @Override
//            public void onFinish(boolean result) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                new Thread(mTask).start();
//            }
//        });
//        new Thread(mTask).start();


//        String str = "0123";
//        String str1 = str.substring(0, 2);
//        String str2 = str.substring(2, 4);
//        System.out.println(str1 + "--------" + str2);

        UserInfo userInfo1 = new UserInfo("张三");
        UserInfo userInfo2 = new UserInfo("李四");
        List<UserInfo> userInfoList = new ArrayList<>();

        userInfoList.add(userInfo1);
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);
        userInfoList.add(userInfo2);
        System.out.println(userInfoList);
        userInfoList.remove(userInfo2);
        System.out.println(userInfoList);
    }
}

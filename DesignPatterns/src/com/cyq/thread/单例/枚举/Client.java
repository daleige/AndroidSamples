package src.com.cyq.thread.单例.枚举;

import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    System.out.println(MyEnum.getInstance().hashCode() + "  --   " + MyEnum.getInstance().toString());
//
//                }
//            };
//            thread.start();
//
//            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                @Override
//                public void uncaughtException(Thread t, Throwable e) {
//                    e.printStackTrace();
//                }
//            });
//
//            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                @Override
//                public void uncaughtException(Thread t, Throwable e) {
//
//                }
//            });
//        }
        String sn = "0000cc311112ccm22152041000731746";
        String str = sn.substring(0, sn.length() - 4);
        System.out.println(str+"  长度:"+str.length());

    }

}

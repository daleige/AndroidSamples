package src.com.cyq.thread.threadlocal;

public class ThreadB extends Thread{

    @Override
    public void run() {
        super.run();
        Tool.threadLocal.set("线程B的私有数据");
    }

    public String getThreadLocalValue(){
        return (String) Tool.threadLocal.get();
    }

}

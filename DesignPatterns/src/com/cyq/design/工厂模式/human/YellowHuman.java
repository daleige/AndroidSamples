package src.com.cyq.design.工厂模式.human;

public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是黄种人，我的肤色是黄色的");
    }

    @Override
    public void talk() {
        System.out.println("黄种人说话一般是算字节");
    }
}

package src.com.cyq.design.工厂模式.human;

public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是白种人 我的肤色是白色的");
    }

    @Override
    public void talk() {
        System.out.println("白种人说话一般是单字节");
    }
}

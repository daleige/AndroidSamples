package src.com.cyq.design.工厂模式.human;

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是黑种人 黑种人的肤色是黑色的");
    }

    @Override
    public void talk() {
        System.out.println("黑种人说话一般听不懂");
    }
}

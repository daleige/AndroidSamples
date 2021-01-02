package src.com.cyq.design.工厂模式.女娲造人;

import src.com.cyq.design.工厂模式.女娲造人.human.BlackHuman;
import src.com.cyq.design.工厂模式.女娲造人.human.Human;
import src.com.cyq.design.工厂模式.女娲造人.human.WhiteHuman;
import src.com.cyq.design.工厂模式.女娲造人.human.YellowHuman;

public class NvWa {

    public static void main(String[] args) {
        AbstractHumanFactory factory = new HumanFactory();
        System.out.println("step1:创建白色人种");
        Human whileHuman = factory.createHuman(WhiteHuman.class);
        whileHuman.getColor();
        whileHuman.talk();

        System.out.println("\nstep2:创建黄色人种");
        Human yellowHuman = factory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();

        System.out.println("\nstep3:创建黑色人种");
        Human blackHuman = factory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
    }
}

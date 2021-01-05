package src.com.cyq.design.构建者模式.经典版建造者模式;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        LenovoBuilder lenovoBuilder=new LenovoBuilder("联想电脑");
        director.makeComputer(lenovoBuilder);
        System.out.println(lenovoBuilder.buildComputer());

        AppleBuilder appleBuilder=new AppleBuilder("苹果电脑");
        director.makeComputer(appleBuilder);
        System.out.println(appleBuilder.buildComputer());
    }
}

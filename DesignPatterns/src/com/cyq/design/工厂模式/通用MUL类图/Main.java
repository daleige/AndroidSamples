package src.com.cyq.design.工厂模式.通用MUL类图;

public class Main {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
        Product product2 = creator.createProduct(ConcreteProduct2.class);
        product1.method1();
        product2.method1();
        product1.method2();
        product2.method2();
    }
}

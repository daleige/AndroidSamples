package src.com.cyq.design.工厂模式.多工厂模式;

public class Main {
    public static void main(String[] args) {
        Factory product1Factory = new Product1Factory();
        Factory product2Factory = new Product2Factory();
        Product1 product1 = (Product1) product1Factory.createProduct();
        Product2 product2 = (Product2) product2Factory.createProduct();

        product1.selfFun();
        product2.selfFun();
    }
}

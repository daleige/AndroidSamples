package src.com.cyq.design.工厂模式.多工厂模式;

public class Product2Factory extends Factory {

    @Override
    public Product2 createProduct() {
        return new Product2();
    }
}

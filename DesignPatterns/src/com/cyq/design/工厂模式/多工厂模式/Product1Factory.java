package src.com.cyq.design.工厂模式.多工厂模式;

import src.com.cyq.design.工厂模式.简单工厂.ProductFactory;

public class Product1Factory extends Factory {

    @Override
    public Product1 createProduct() {
        return new Product1();
    }
}

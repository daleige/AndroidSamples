package src.com.cyq.design.工厂模式.简单工厂;

import src.com.cyq.design.工厂模式.通用MUL类图.Product;

/**
 * 简单工厂：一个模块仅需要一个工厂，没有必要创建一个抽象的工厂类
 */
public class ProductFactory {

    public static <T extends Product> T createProduct(Class c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}

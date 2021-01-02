package src.com.cyq.design.工厂模式.延迟加载;

import src.com.cyq.design.工厂模式.多工厂模式.Product;
import src.com.cyq.design.工厂模式.多工厂模式.Product1;
import src.com.cyq.design.工厂模式.多工厂模式.Product2;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟加载工厂类：定义一个map存放所有的Product，如果map中有则直接返回，没有则生产并存入map中
 */
public class ProductFactory {
    private static final Map<String, Product> productMap = new HashMap<>();

    private static synchronized Product createProduct(String type) {
        Product product = null;
        if (productMap.containsKey(type)) {
            product = productMap.get(type);
        } else {
            if (type.equals("product1")) {
                product = new Product1();
            } else if (type.equals("product2")) {
                product = new Product2();
            }
            productMap.put(type, product);
        }
        return product;
    }
}

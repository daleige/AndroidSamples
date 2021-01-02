package src.com.cyq.design.工厂模式.通用MUL类图;

public abstract class Creator {
    /**
     * 创建产品类，阐述类型可以为Class,String,Enum,也可以为空
     *
     * @param c
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> c);
}

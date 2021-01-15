package src.com.cyq.design.中介者模式.中介者进销存;

/**
 * 定义一个抽象中介者
 */
public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Stock stock;
    protected Sale sale;

    public AbstractMediator() {
        this.purchase = new Purchase(this);
        this.stock = new Stock(this);
        this.sale = new Sale(this);
    }

    public abstract void execute(String str, Object... objects);
}

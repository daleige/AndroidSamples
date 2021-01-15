package src.com.cyq.design.中介者模式.中介者进销存;

public class Main {
    public static void main(String[] args) {
        AbstractMediator mediator = new Mediator();
        Purchase purchase = new Purchase(mediator);
        //采购电脑
        purchase.buyIBMComputer(50);
        Sale sale = new Sale(mediator);
        //销售人员销售电脑
        sale.sellIBMComputer(60);
        Stock stock = new Stock(mediator);
        //库存管理人员清仓电脑
        stock.clearStock();
    }
}

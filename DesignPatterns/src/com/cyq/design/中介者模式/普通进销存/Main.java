package src.com.cyq.design.中介者模式.普通进销存;

public class Main {

    public static void main(String[] args) {
        Purchase purchase = new Purchase();
        //采购电脑
        purchase.buyIBMComputer(50);
               Sale sale = new Sale();
        //销售人员销售电脑
        sale.sellIBMComputer(200);
        Stock stock=new Stock();
        //库存管理人员清仓电脑
        stock.clearStock();
    }
}

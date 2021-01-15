package src.com.cyq.design.中介者模式.普通进销存;

public class Purchase {

    /**
     * 如果销量大于80台就继续采购，如果销量小于80就只采购一半
     *
     * @param number
     */
    public void buyIBMComputer(int number) {
        Stock stock = new Stock();
        Sale sale = new Sale();
        if (sale.getSaleStatus() > 80) {
            System.out.println("采购部：采购" + number + "台IBM电脑");
            stock.increase(number);
        } else {
            System.out.println("采购部：销售情况不好，只采购" + number / 2 + "台IBM电脑");
        }
    }

    public void refuseBuyIBM() {
        System.out.println("采购部：决绝采购IBM电脑");
    }
}

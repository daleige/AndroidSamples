package src.com.cyq.design.中介者模式.普通进销存;

import java.util.Random;

public class Sale {


    /**
     * 销售电脑
     *
     * @param number
     */
    public void sellIBMComputer(int number) {
        Purchase purchase = new Purchase();
        Stock stock = new Stock();
        if (stock.getStockNumber() < number) {
            purchase.buyIBMComputer(number);
        }
        System.out.println("销售部：卖出了" + number + "台IBM电脑");
        stock.decrease(number);
    }

    public int getSaleStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("销售部：IMB销售情况：卖出" + saleStatus + "台");
        return saleStatus;
    }

    public void offSale() {
        Stock stock = new Stock();
        System.out.println("销售部：折扣价清仓销售" + stock.getStockNumber() + "台IBM电脑");
    }
}

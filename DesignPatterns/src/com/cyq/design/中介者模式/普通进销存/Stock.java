package src.com.cyq.design.中介者模式.普通进销存;

public class Stock {
    private static int COMPUTER_NUMBER = 100;

    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("库存部：库存增加" + number + "台");
    }

    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("库存部：库存减少" + number + "台");
    }

    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    /**
     * 库存压力大
     */
    public void clearStock() {
        Purchase purchase = new Purchase();
        Sale sale = new Sale();
        System.out.println("库存部：当前库存压力大，库存为" + COMPUTER_NUMBER + ",需要销售打折销售并停止采购");
        sale.offSale();
        purchase.refuseBuyIBM();
    }
}

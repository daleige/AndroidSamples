package src.com.cyq.design.中介者模式.中介者进销存;

import java.util.Random;

public class Sale extends AbstractColleague{

    public Sale(AbstractMediator _mediator) {
        super(_mediator);
    }

    /**
     * 销售电脑
     *
     * @param number
     */
    public void sellIBMComputer(int number) {
        super.mediator.execute("sale.sell",number);
    }

    public int getSaleStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("销售部：IMB销售情况：卖出" + saleStatus + "台");
        return saleStatus;
    }

    public void offSale() {
        super.mediator.execute("sale.offSale");
    }
}

package src.com.cyq.design.中介者模式.中介者进销存;

public class Stock extends AbstractColleague {
    private static int COMPUTER_NUMBER = 100;

    public Stock(AbstractMediator _mediator) {
        super(_mediator);
    }

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
        super.mediator.execute("stock.clear");
    }
}

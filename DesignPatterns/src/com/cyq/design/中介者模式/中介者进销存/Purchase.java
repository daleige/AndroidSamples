package src.com.cyq.design.中介者模式.中介者进销存;

public class Purchase extends AbstractColleague{

    public Purchase(AbstractMediator _mediator) {
        super(_mediator);
    }

    /**
     * 如果销量大于80台就继续采购，如果销量小于80就只采购一半
     *
     * @param number
     */
    public void buyIBMComputer(int number) {
        super.mediator.execute("purchase.buy",number);
    }

    public void refuseBuyIBM() {
        System.out.println("采购部：决绝采购IBM电脑");
    }
}

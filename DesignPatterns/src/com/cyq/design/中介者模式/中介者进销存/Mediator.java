package src.com.cyq.design.中介者模式.中介者进销存;

public class Mediator extends AbstractMediator {

    @Override
    public void execute(String str, Object... objects) {
        switch (str) {
            case "purchase.buy":
                this.buyComputer((Integer) objects[0]);
                break;
            case "sale.sell":
                this.sellComputer((Integer) objects[0]);
                break;
            case "sale.offSale":
                this.offSale();
                break;
            case "stock.clear":
                this.clearStock();
                break;
        }
    }

    /**
     * 购买电脑
     *
     * @param number 购买数量
     */
    private void buyComputer(Integer number) {
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80) {
            System.out.println("采购部：采购" + number + "台IBM电脑");
            super.stock.increase(number);
        } else {
            System.out.println("采购部：销售情况不好，只采购" + number / 2 + "台IBM电脑");
        }
    }

    /**
     * 销售电脑
     *
     * @param number 销售数量
     */
    private void sellComputer(Integer number) {
        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMComputer(number);
        }
        System.out.println("销售部：卖出了" + number + "台IBM电脑");
        super.stock.decrease(number);
    }

    /**
     * 折扣销售
     */
    private void offSale() {
        System.out.println("销售部：折扣价清仓销售" + super.stock.getStockNumber() + "台IBM电脑");
    }

    /**
     * 清仓
     */
    private void clearStock() {
        System.out.println("库存部：当前库存压力大，库存为" + super.stock.getStockNumber() + ",需要销售打折销售并停止采购");
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }
}

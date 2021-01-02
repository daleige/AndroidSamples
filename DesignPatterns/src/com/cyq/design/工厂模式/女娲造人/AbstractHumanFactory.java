package src.com.cyq.design.工厂模式.女娲造人;

import src.com.cyq.design.工厂模式.女娲造人.human.Human;

public abstract class AbstractHumanFactory {

    /**
     * @param c   必须是Class类型
     * @param <T> 必须是Human实现类
     * @return 具体的Human实现类
     */
    public abstract <T extends Human> T createHuman(Class<T> c);
}

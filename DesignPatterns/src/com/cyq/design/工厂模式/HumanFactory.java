package src.com.cyq.design.工厂模式;

import src.com.cyq.design.工厂模式.human.Human;

public class HumanFactory extends AbstractHumanFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) human;
    }
}

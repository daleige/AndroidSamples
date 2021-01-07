package src.com.cyq.design.代理模式.通用动态代理;

public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("执行方法之前....");
    }
}

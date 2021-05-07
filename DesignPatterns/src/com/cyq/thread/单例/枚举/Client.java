package src.com.cyq.thread.单例.枚举;

public class Client {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(MyEnum.getInstance().hashCode()+"  --   "+MyEnum.getInstance().toString());
        }
    }

}

package src.com.cyq.thread.单例.枚举;

public class MyEnum {

    public enum MyEnumSingleton {
        userInfoFactory;

        private final UserInfo userInfo;

        MyEnumSingleton() {
            userInfo = new UserInfo();
            userInfo.setUsername("张三");
            userInfo.setAge(19);
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }
    }

    public static UserInfo getInstance() {
        return MyEnumSingleton.userInfoFactory.getUserInfo();
    }
}

package src.com.cyq.design;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String str = getuuid();
            System.out.println("长度：" + str.length() + "\t" + str);
        }
    }

    public static String getuuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}

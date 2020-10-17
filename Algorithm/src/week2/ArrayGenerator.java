package week2;

import java.util.Random;

/**
 * 生成测试数据
 */
public class ArrayGenerator {

    public ArrayGenerator() {
    }

    /**
     * 生成[0...n)的区间的有序数组
     *
     * @param n
     * @return
     */
    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 生成一个长度为n的随机数组，妹子数字的范围是[0...n)
     *
     * @param n
     * @param bound
     * @return
     */
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }
}

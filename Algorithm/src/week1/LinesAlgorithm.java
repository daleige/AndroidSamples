package week1;

public class LinesAlgorithm {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 67, 43, 24, 90};
        System.out.println("下标：" + getDataIndex(data, 4));

        Integer[] data2 = {1, 2, 3, 4, 67, 43, 24, 90};
        System.out.println("下标：" + getDataIndex2(data2, 3));

        Character[] data3 = {'1', 'T', 'a', 'c', 'd', 'F'};
        System.out.println("下标：" + getDataIndex2(data3, 'a'));
    }

    public static int getDataIndex(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 定义fangxing
     * @param data
     * @param target
     * @param <T>
     * @return
     */
    public static <T> int getDataIndex2(T[] data, T target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

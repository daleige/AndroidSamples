package week2;

public class InsertionSort {

    /**
     * 插入排序法实现
     *
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    E t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 插入排序优化方案
     *
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }

    /**
     * 从右到左实现插入排序
     *
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            E t = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && t.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = t;
        }
    }
}

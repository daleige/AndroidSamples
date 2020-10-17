package week2;

public class SortingHelper {
    public SortingHelper() {

    }

    /**
     * 判断arr数组是不是从小到大排列的
     *
     * @param arr
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用来测试排序 和时间复杂度
     *
     * @param sort
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortTest(Sort sort, E[] arr) {
        long startTime = System.nanoTime();
        switch (sort) {
            case 选择排序:
                SelectionSort.sort2(arr);
                break;
            case 插入排序:

                break;
        }
        long endTime = System.nanoTime();
        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException("selectionSort fail");
        }
        double time = (endTime - startTime) / 1000000000F;
        System.out.println(String.format("%s ,数据量n= %d ,耗时：%f", sort.toString(), arr.length, time));
    }

    /**
     * 定义排序算法类型
     */
    public enum Sort {
        选择排序,
        插入排序
    }
}

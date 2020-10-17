package week2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n1 = 10000, n2 = 100000;
        Integer[] arr1;
        Integer[] arr2;
        arr1 = ArrayGenerator.generateRandomArray(n1, n1);
        arr2 = ArrayGenerator.generateRandomArray(n2, n2);

        Integer[] selectorArr1 = Arrays.copyOf(arr1, arr1.length);
        Integer[] selectorArr2 = Arrays.copyOf(arr2, arr2.length);
        SortingHelper.sortTest(SortingHelper.Sort.选择排序, selectorArr1);
        SortingHelper.sortTest(SortingHelper.Sort.选择排序, selectorArr2);

        Integer[] insertArr1 = Arrays.copyOf(arr1, arr1.length);
        Integer[] insertArr2 = Arrays.copyOf(arr2, arr2.length);
        SortingHelper.sortTest(SortingHelper.Sort.插入排序, insertArr1);
        SortingHelper.sortTest(SortingHelper.Sort.插入排序, insertArr2);

        Integer[] insertArr11 = Arrays.copyOf(arr1, arr1.length);
        Integer[] insertArr22 = Arrays.copyOf(arr2, arr2.length);
        SortingHelper.sortTest(SortingHelper.Sort.插入排序优化, insertArr11);
        SortingHelper.sortTest(SortingHelper.Sort.插入排序优化, insertArr22);
    }
}

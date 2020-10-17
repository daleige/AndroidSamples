package week2;

import java.util.Objects;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 5, 2, 3, 2, 18, 7};
        SelectionSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        Integer[] arr2 = {1, 4, 6, 5, 2, 3, 2, 18, 7};
        SelectionSort.sort2(arr2);
        for (Integer i : arr2) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        Double[] arr3 = {1.2, 4.1, 6.0, 5.3, 2.1, 3.6, 2.1, 18.0, 7.4};
        SelectionSort.sort2(arr3);
        for (Double i : arr3) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        Student[] students = {new Student("张三", 60),
                new Student("李四", 70),
                new Student("王五", 90),
                new Student("赵六", 67),
                new Student("二麻子", 70)};
        SelectionSort.sort2(students);
        for (Student student : students) {
            System.out.print(student.toString() + "\t");
        }
    }

    /**
     * 选择排序 依次从列表中找到最小的一位，并放在前面
     *
     * @param arr
     */
    public static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //交换前面的和最小的两个的位置
            if (minIndex != i) {
                int item = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = item;
            }
        }
    }

    /**
     * 泛型方式实现选择排序
     *
     * @param arr
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            //交换前面的和最小的两个的位置
            if (minIndex != i) {
                E item = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = item;
            }
        }
    }

    /**
     * 学生类 比较分数
     */
    public static class Student implements Comparable<Student> {
        private final String name;
        private final int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student object) {
            return this.score - object.score;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object)
                return true;
            if (object == null || getClass() != object.getClass())
                return false;
            Student student = (Student) object;
            return score == student.score &&
                    Objects.equals(name, student.name);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}

package week3;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(10);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addLast(111);
        System.out.println(arr);

        arr.removeLast();
        arr.removeLast();
        arr.removeLast();arr.removeLast();arr.removeLast();arr.removeLast();arr.removeLast();arr.removeLast();arr.removeLast();
        System.out.println(arr);

//        System.out.println(arr.remove(3));
//        System.out.println(arr);
//
//        System.out.println(arr.removeFirst());
//        System.out.println(arr);
//
//        System.out.println(arr.removeLast());
//        System.out.println(arr);
    }
}

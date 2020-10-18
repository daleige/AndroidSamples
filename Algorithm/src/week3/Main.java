package week3;

public class Main {
    public static void main(String[] args) {
        Array arr = new Array(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        System.out.println(arr.remove(3));
        System.out.println(arr);

        System.out.println(arr.removeFirst());
        System.out.println(arr);

        System.out.println(arr.removeLast());
        System.out.println(arr);
    }
}

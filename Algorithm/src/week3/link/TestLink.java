package week3.link;

public class TestLink {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 888);
        System.out.println("index 2 插入888" + linkedList);
        System.out.println("是否包含888:" + linkedList.container(888));
        System.out.println("是否包含999:" + linkedList.container(999));
        linkedList.addLast(666);
        System.out.println("尾部插入666:" + linkedList);
        linkedList.set(4, 555);
        System.out.println("修改下标4的值为555:" + linkedList);
        linkedList.remove(4);
        System.out.println("移除下标4的值为555:" + linkedList);
        linkedList.removeFirst();
        System.out.println("移除前面一个:" + linkedList);
        linkedList.removeLast();
        System.out.println("移除最后面一个:" + linkedList);
    }
}

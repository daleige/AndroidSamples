package week3;

public class ArrayStack<E> implements Stack<E> {
    Array array;

    public ArrayStack(int capacity) {
        this.array = new Array(capacity);
    }

    public ArrayStack() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public void pop() {

    }

    @Override
    public void peek() {

    }
}

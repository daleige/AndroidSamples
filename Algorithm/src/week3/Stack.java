package week3;

public interface Stack<E> {
    /**
     * 获取长度大小
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入栈一个元素
     * @param e
     */
    void push(E e);

    /**
     * 出栈一个元素
     */
    void pop();

    /**
     * 获取栈顶元素
     */
    void peek();
}
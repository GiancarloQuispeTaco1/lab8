package lab8;

import java.util.EmptyStackException;

public class Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
	public Stack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void push(E item) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = item;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E item = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        @SuppressWarnings("unchecked")
		E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    
}


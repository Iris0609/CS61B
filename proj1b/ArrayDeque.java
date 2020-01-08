
public class ArrayDeque<T> implements Deque<T> {
    //public T[] a = (T[]) new Object[1000];
    private T[] arr;
    private int size;
    private int nextFirst, nextLast;
    private static final int R_FACTOR = 2;
    private static final double MINRATIO = 0.25;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int minusOne(int index) {
        if (index - 1 < 0) {
            return arr.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index + 1 >= arr.length) {
            return 0;
        }
        return index + 1;

    }
    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[current];
            current = plusOne(current);
        }
        arr = newArr;
        nextFirst = arr.length - 1;
        nextLast = size;

    }

    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            resize(size * R_FACTOR);
        }

        arr[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            resize(size * R_FACTOR);
        }

        arr[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        int current = plusOne(nextFirst);
        while (current != nextLast) {
            System.out.print(arr[current]);
            System.out.print(' ');
            current = plusOne(current);
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        T item = arr[first];
        arr[first] = null;
        nextFirst = first;
        size -= 1;

        if (arr.length >= 16 && size <= arr.length * MINRATIO) {
            resize(arr.length / R_FACTOR);
        }
        return item;

    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        T item = arr[last];
        arr[last] = null;
        nextLast = last;
        size -= 1;
        if (arr.length >= 16 && size <= arr.length * MINRATIO) {
            resize(arr.length / R_FACTOR);
        }
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int first = plusOne(nextFirst);
        int current = first + index;
        if (current >= arr.length) {
            return arr[current - arr.length];
        }

        return arr[current];
    }

}

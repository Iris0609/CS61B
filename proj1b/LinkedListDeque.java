public class LinkedListDeque<T> implements Deque<T> {
    private TNode sentinelFirst;
    private TNode sentinelLast;
    private int size;

    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinelFirst = new TNode(null,  null, null);
        sentinelLast = new TNode(null, null, null);
        sentinelFirst.next = sentinelLast;
        sentinelLast.prev = sentinelFirst;
        sentinelFirst.prev = sentinelLast;
        sentinelLast.next = sentinelFirst;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode node = new TNode(item, sentinelFirst, sentinelFirst.next);
        sentinelFirst.next = node;
        node.next.prev = node;
        size += 1;

    }

    @Override
    public void addLast(T item) {
        TNode node = new TNode(item, sentinelLast.prev, sentinelLast);
        sentinelLast.prev = node;
        node.prev.next = node;
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
        TNode p = sentinelFirst.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode first = sentinelFirst.next;
        T i = first.item;
        sentinelFirst.next = first.next;
        first.next.prev = sentinelFirst;
        first.prev = null;
        first.next = null;
        first.item = null;

        size -= 1;
        return i;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode last = sentinelLast.prev;
        T i = last.item;
        sentinelLast.prev = last.prev;
        last.prev.next = sentinelLast;
        last.prev = null;
        last.next = null;
        last.item = null;
        size -= 1;
        return i;

    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        TNode p = sentinelFirst.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private T helperRecursive(TNode p, int ind) {
        if (ind == 0) {
            return p.item;
        }
        return helperRecursive(p.next, ind - 1);
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return helperRecursive(sentinelFirst.next, index);
    }

}
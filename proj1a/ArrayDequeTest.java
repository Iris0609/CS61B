public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> a =new ArrayDeque<>();
        a.addFirst(10);
        a.addLast(20);
        a.addFirst(5);
        a.addLast(25);
        a.addLast(30);
        a.addLast(35);
        a.addFirst(10);
        a.addLast(20);
        a.addFirst(5);
        a.addLast(25);
        a.addLast(30);
        a.addLast(35);
        a.printDeque();

    }
}

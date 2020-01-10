package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertFalse(arb.isEmpty());
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        assertEquals((Object) arb.dequeue(),(Object) 1);
        assertEquals((Object)arb.peek(), (Object) 1);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        assertTrue(arb.isFull());

        for(Integer i: arb){
            assertNotNull(i);
        }






    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {
    @Test
    public void testConnect(){
        UnionFind uf = new UnionFind(8);
        uf.union(0,1);
        uf.union(1,2);
        assertEquals(1,uf.parent(0));
        assertEquals(1, uf.find(0));
        uf.union(0,3);
        assertEquals(1,uf.parent(3));
        uf.union(5,6);
        uf.union(3,7);
        assertEquals(-1,uf.parent(1));
        assertEquals(1,uf.parent(7));
        assertEquals(5, uf.sizeOf(1));
        uf.union(5,7);
        assertEquals(6,uf.parent(5));
        assertEquals(1,uf.parent(6));
        assertEquals(1,uf.find(6));



    }
}

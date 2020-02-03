import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {

    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */

    private Map<Integer, Integer> hm;
    private List<Integer> weighted;
    public UnionFind(int n) {
        // TODO - Using Map to hold the sets
        hm = new HashMap<>(n);
        // Intialized weighted with size 1
        weighted = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            weighted.add(1);
            hm.put(i , -1);
        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO -  check whether hm contains key
        if (! hm.containsKey(vertex)) {
            throw new RuntimeException("it is not a valid index");
        }
        return;
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        int p = find(v1);
        return weighted.get(p);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        int res = hm.get(v1);
        return res;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        int r1 = find(v1);
        int r2 = find(v2);

        return r1 == r2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);
        int w1 = sizeOf(r1);
        int w2 = sizeOf(r2);
        if (w1 <= w2) {
            //hm.put(r1, v2);
            hm.put(r1, r2);
            weighted.set(r2, w1 + w2);
           // weighted.set(v1, 0);
        } else {
            hm.put(r2, r1);
            weighted.set(r1, w1 + w2);
           // weighted.set(r2, 0);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    private int dfs(int vertex){
        int p = parent(vertex);
        if (p == -1) {
            return vertex;
        }

        hm.put(vertex, dfs(p));
        return hm.get(vertex);



    }


    public int find(int vertex) {
        // TODO
        return dfs(vertex);
    }

}

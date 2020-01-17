package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class World implements Serializable{
    private final static int MINSIZE = 5;
    private int minWidth;
    private int minHeight;
    private BSPNode root;
    private Random RANDOM;
    private TETile[][] world;
    private Queue<BSPNode> leaves;


    public class BSPNode implements Serializable{
        int startx;
        int starty;
        int endx;
        int endy;
        int size;
        Room room = null;
        BSPNode left = null;
        BSPNode right = null;
        BSPNode parent = null;

        public BSPNode(int sx, int sy, int ex, int ey) {
            startx = sx;
            starty = sy;
            endx = ex;
            endy = ey;
            size = (endx - startx) * (endy - starty);
            room = null;
            left = null;
            right = null;
            parent = null;

        }
    }

    public class Room implements Serializable{
        int left;
        int right;
        int top;
        int bottom;

    }

    public World(int wide, int height, int seed) {
        root = new BSPNode(0, 0, wide, height);
        RANDOM = new Random(seed);
        world = new TETile[wide][height];
        minHeight = height;
        minWidth = wide;
        leaves = new LinkedList<>();
        leaves.add(root);


        for (int x = 0; x < wide; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

    }

    private List<BSPNode> split(BSPNode node){
        //decide the direction: 0-> x, 1-> y, generate the split range
        int direction = RANDOM.nextInt(2);
        //split node and return left and right children;
        List<BSPNode> children = new ArrayList<>(2);
        //BSPNode[] children = (BSPNode[]) new Object[2];
        BSPNode left;
        BSPNode right;
        int range = 0;
        int pos = 0;
        if (direction == 0) {

                range = (int) Math.ceil((node.endx - node.startx)/3.0);
                pos = range + RANDOM.nextInt(range);
            } else {
                range = (int) Math.ceil((node.endy - node.starty)/3.0);
                pos = range+ RANDOM.nextInt(range);
            }



        //generate children node, left child is the left or bottom part, right child is the right or top part

        if (direction == 0){
            left = new BSPNode(node.startx, node.starty, node.startx + pos, node.endy);
            right = new BSPNode(node.startx + pos, node.starty, node.endx, node.endy);
            int minside = Math.min(pos, node.endx - node.startx - pos);
            minWidth = Math.min(minWidth, minside);
            /**visualize for test
            for (int y = node.starty; y < node.endy; y++) {
                world[node.startx + pos][y] = Tileset.FLOWER;
            }*/
        } else {
            left = new BSPNode(node.startx, node.starty, node.endx, node.starty + pos);
            right = new BSPNode(node.startx, node.starty + pos, node.endx, node.endy);
            int minside = Math.min(pos, node.endy - node.starty - pos);
            minHeight = Math.min(minHeight, minside);
            /**visualize for test
            for (int x = node.startx; x < node.endx; x++){
                world[x][node.starty + pos] = Tileset.FLOWER;
            }*/
        }
        left.parent = node;
        right.parent = node;

        children.add(left);
        children.add(right);

        return children;
    }

    private void BSPSplit() {
        // stop split the node if any node.width or node.height < MINSIZE
        while (minWidth > MINSIZE && minHeight > MINSIZE) {
            // save the new leaves after split
            Queue<BSPNode> temp = new LinkedList<>();
            // BFS split node
            while (leaves.size() != 0) {
                BSPNode node = leaves.poll();
                List<BSPNode> children = split(node);
                BSPNode left = children.get(0);
                BSPNode right = children.get(1);
                node.left = left;
                node.right = right;
                temp.add(left);
                temp.add(right);
            }
            leaves = temp;

        }

    }

    private Room createRoom(BSPNode node) {


        Room r = new Room();
        int xrange = node.endx - node.startx;
        int yrange = node.endy - node.starty;
        r.left = node.startx + RANDOM.nextInt((int) Math.ceil(xrange/3.0));
        r.right = node.endx - RANDOM.nextInt((int) Math.ceil(xrange/3.0));
        if (r.right == node.endx && r.right - r.left > 2) {
            r.right -= 1;
        }
        if(r.left == node.startx && r.right - r.left > 2){
            r.left += 1;
        }

        r.bottom = node.starty + RANDOM.nextInt((int) Math.ceil(yrange/3.0));
        r.top = node.endy - 1 - RANDOM.nextInt((int) Math.ceil(yrange/3.0));
        if (r.top == node.endy && r.top - r.bottom > 2) {
            r.top -= 1;
        }
        if(r.bottom ==node.starty && r.top - r.bottom > 2) {
            r.bottom += 1;
        }
        //visualize for test
        for (int x = r.left; x < r.right + 1 ; x ++){
            world[x][r.bottom] = Tileset.WALL;
            world[x][r.top ] = Tileset.WALL;
        }

        for (int y = r.bottom; y < r.top + 1 ; y++) {
            world[r.left][y] = Tileset.WALL;
            world[r.right ][y] = Tileset.WALL;
        }

        for (int x = r.left + 1; x < r.right; x++) {
            for (int y = r.bottom + 1; y < r.top; y++) {
                    world[x][y] = Tileset.FLOOR;

            }
        }

        return r;

    }

    private void generateRoom() {
        // go through all the leaves and generate random size room for each leaf
        for (BSPNode l : leaves) {
            l.room = createRoom(l);
        }


    }

    private void createHall(BSPNode left, BSPNode right) {
        int w = world.length;
        int h = world[0].length;

        if (left.startx == right.startx) {
            //parent split horizontal
            int range = (int) Math.ceil((left.endx - left.startx - 2) / 3.0);
            int x = left.startx + range + RANDOM.nextInt(range) + 1;
            int y = left.endy;


            while(y < h && world[x][y] != Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x - 1][y] = Tileset.WALL;
                world[x + 1][y] = Tileset.WALL;
                y += 1;
            }

            if (y == h - 1) {
                world[x][h - 1] = Tileset.WALL;
            }
            y = left.endy - 1;

            while(y >= 0 && world[x][y] != Tileset.FLOOR){
                world[x][y] = Tileset.FLOOR;
                world[x - 1][y] = Tileset.WALL;
                world[x + 1][y] = Tileset.WALL;
                y -= 1;
            }

            if (y < 0) {
                world[x][0] = Tileset.WALL;
            }

        } else if (left.starty == right.starty ){
            //parent split vertical
            int range = (int) Math.ceil((left.endy - left.starty - 2) /3.0);
            int x = left.endx;
            int y = left.starty + range + RANDOM.nextInt(range) + 1;

            while(x < w && world[x][y] != Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x][y - 1] = Tileset.WALL;
                world[x][y + 1] = Tileset.WALL;
                x += 1;
            }

            if (x == w) {
                world[w - 1][y] = Tileset.WALL;
            }

            x = left.endx - 1;

            while(x >= 0 && world[x][y] != Tileset.FLOOR){
                world[x][y] = Tileset.FLOOR;
                world[x][y - 1] = Tileset.WALL;
                world[x][y + 1] = Tileset.WALL;
                x -= 1;
            }
            if (x < 0) {
                world[0][y] = Tileset.WALL;
            }
        }


    }


    private void generateHall(){
        //Queue<BSPNode> temp = new LinkedList<>();
        while (leaves.size() > 1){
            BSPNode left = leaves.poll();
            BSPNode right = leaves.poll();
            if(left.parent != right.parent) {
                throw new RuntimeException("There is something wrong with the bsp tree construction");

            }
            leaves.add(left.parent);
            createHall(left, right);

        }


    }

    public TETile[][] getWorld(){
        return this.world;
    }

    public TETile[][] generateWorld(){
        BSPSplit();
        generateRoom();
        generateHall();

        return world;
    }

}

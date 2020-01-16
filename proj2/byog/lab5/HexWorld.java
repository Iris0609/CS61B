package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int Width;
    private int Height;
    private TERenderer ter;
    private TETile[][] world;
    private int Seed;
    private Random RANDOM;
    private int Size;

    public HexWorld() {
        Size = 3;
        Seed = 1000;
        RANDOM = new Random(Seed);
        Width = 60;
        Height = 30;
        init();
    }



    public HexWorld(int size) {
        Size = size;
        Seed = 1000;
        RANDOM = new Random(Seed);
        Height = Size * 2 * 5 + 4;
        Width = (Size + (Size - 1) * 2) * 3 + Size * 2 + 6;

        init();
    }

    public HexWorld(int seed, int size) {
        Size = size;
        Seed = seed;
        RANDOM = new Random(Seed);
        Height = Size * 2 * 5 + 4;
        Width = (Size + (Size - 1) * 2) * 3 + Size * 2 + 6;

        init();
    }


    private void init() {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        ter = new TERenderer();
        ter.initialize(Width, Height);

        // initialize tiles
        world = new TETile[Width][Height];
        for (int x = 0; x < Width; x += 1) {
            for (int y = 0; y < Height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

    }

    private int levelSize(int level, int s) {
        int ls;
        if (level < s) {
            ls = s + 2 * level;
        }
        else{
            ls = s + 2 * (2 * s - level - 1);
        }
        return ls;
    }

    private int levelStart(int level, int s, int startx) {
        int ls;
        if (level < s) {
            ls = startx - level;
        }
        else {
            ls = startx - (2 * s - level - 1);
        }
        return ls;
    }

    private TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.TREE;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.GRASS;
        }
    }


    public void addHexagon(int startx, int starty) {
        int x = startx;
        int y = starty;
        int s = Size;
        TETile type = randomTile();

        for (int l = 0; l < 2*s;  l++) {
            y = starty + l;
            if (y < 0 || y >= Height) {
                throw new RuntimeException("The world is not high enough");
            }
            int levelsize = levelSize(l, s);
            int levelstart = levelStart(l, s, startx);
            for (x =  levelstart; x < levelstart + levelsize; x++){
                if (x < 0 || x >= Width) {
                    throw new RuntimeException("The world is not wide enough");
                }
                world[x][y] = type;
            }

        }

    }

    public void display() {
        ter.renderFrame(world);

    }


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        HexWorld hw = new HexWorld(100, 5);
        int initx = 3;
        int inity = 2;

        for (int i = 0; i < 5; i++) {
            if (i == 0){
                int x = initx + hw.Size - 1;
                hw.addHexagon(x, inity + 2 * hw.Size);
                hw.addHexagon(x, inity + 4 * hw.Size);
                hw.addHexagon(x, inity + 6 * hw.Size);
                }
            else if (i == 1){
                int x = initx + hw.Size + 2 * (hw.Size - 1);
                hw.addHexagon(x, inity + hw.Size);
                hw.addHexagon(x, inity + 3 * hw.Size);
                hw.addHexagon(x, inity + 5 * hw.Size);
                hw.addHexagon(x, inity + 7 * hw.Size);
                }
            else if (i == 2) {
                int x =  initx + 2 * hw.Size + 3 * (hw.Size - 1);
                hw.addHexagon(x, inity);
                hw.addHexagon(x, inity + 2 * hw.Size);
                hw.addHexagon(x, inity + 4 * hw.Size);
                hw.addHexagon(x, inity + 6 * hw.Size);
                hw.addHexagon(x, inity + 8 * hw.Size);
            }
            else if(i == 3){
                int x =  initx + 3 * hw.Size + 4 * (hw.Size - 1);
                hw.addHexagon(x, inity + hw.Size);
                hw.addHexagon(x, inity + 3 * hw.Size);
                hw.addHexagon(x, inity + 5 * hw.Size);
                hw.addHexagon(x, inity + 7 * hw.Size);
            } else {
                int x = initx + 4 * hw.Size + 5 * (hw.Size - 1);
                hw.addHexagon(x, inity + 2 * hw.Size);
                hw.addHexagon(x, inity + 4 * hw.Size);
                hw.addHexagon(x, inity + 6 * hw.Size);

            }
            }

        // draws the world to the screen
        hw.display();


        }



}

package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player extends Role {
    boolean win;
    TETile[][] map;
    public int mapwidth;
    public int mapheight;


    public Player(int x, int y, TETile[][] world) {
        this.updatePos(x, y);
        this.map = world;
        mapwidth = map.length;
        mapheight = map[0].length;
        this.mark = Tileset.PLAYER;

    }

    @Override
    public boolean Blocked(int x, int y) {
        if (map[x][y] != Tileset.FLOOR) {
            return true;
        }

        //need to one more logic to check whether play win

        return false;
    }
}

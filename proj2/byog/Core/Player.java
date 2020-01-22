package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player extends Role {
    boolean win;
    World world;
    TETile[][] map;
    public int mapwidth;
    public int mapheight;


    public Player(int x, int y, World world) {
        this.updatePos(x, y);
        this.world = world;
        this.map = world.getWorld();
        mapwidth = map.length;
        mapheight = map[0].length;
        this.mark = Tileset.PLAYER;

    }


    @Override
    public boolean Blocked(int x, int y) {
        TETile target = map[x][y];

        if (!target.equals(Tileset.FLOOR)) {
            return true;
        }
        //need to one more logic to check whether play win
        return false;
    }
}

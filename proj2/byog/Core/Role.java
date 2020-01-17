package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public abstract class Role implements Movement, Serializable {
    private int posx;
    private int posy;
    public TETile mark;

    public int[] getCurrentPos() {
        int[] pos = new int[2];
        pos[0] = posx;
        pos[1] = posy;
        return pos;
    }

    public void updatePos(int x, int y) {
        posx = x;
        posy = y;
    }

    @Override
    public abstract boolean Blocked(int x, int y);

    @Override
    public void Move(char action) {
        if (action == 'a') {
            MoveLeft();
        } else if (action =='d') {
            MoveRight();
        } else if(action == 'w') {
            MoveUp();
        } else if(action == 's') {
            MoveDown();
        }



    }

    protected void MoveLeft() {
        int[] pos = getCurrentPos();
        int curx = pos[0];
        int cury = posy;
        if (Blocked(curx - 1, cury)){
            return;
        }
        updatePos(curx - 1, cury);
    }

    protected void MoveRight() {
        int[] pos = getCurrentPos();
        int curx = pos[0];
        int cury = posy;
        if (Blocked(curx + 1, cury)){
            return;
        }
        updatePos(curx + 1, cury);
    }
    protected void MoveUp() {
        int[] pos = getCurrentPos();
        int curx = pos[0];
        int cury = posy;
        if (Blocked(curx, cury + 1)){
            return;
        }
        updatePos(curx, cury + 1);
    }
    protected void MoveDown() {
        int[] pos = getCurrentPos();
        int curx = pos[0];
        int cury = posy;
        if (Blocked(curx, cury - 1)){
            return;
        }
        updatePos(curx, cury - 1);
    }


}

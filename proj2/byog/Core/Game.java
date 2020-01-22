package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Stack;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 120;
    public static final int HEIGHT = 60;
    private World world;
    private Player player;


    public Game(){


    }

    /** for personal testing
    public World W;

    public void displayWorld(){
        TETile[][] world = null;
        W = new World(WIDTH, HEIGHT, 1);
        world = W.generateWorld();
        ter.initialize(WIDTH, HEIGHT);

        ter.renderFrame(world);
    }
     */

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        String lcInput = input.toLowerCase();

        TETile[][] finalWorldFrame = null;
        int p = 1;

        if (lcInput.charAt(0) == 'n') {
            String seed = "";

            char c = lcInput.charAt(p);
            while (c - '0' < 10  && c -'0' > -1) {
                seed = seed + c;
                p += 1;
                c = lcInput.charAt(p);
            }
            int Seed = Integer.parseInt(seed);
            world = new World(WIDTH, HEIGHT, Seed);
            world.generateWorld();
            player = world.getPlayer();
        } else if (lcInput.charAt(0) == 'l') {
            p = 1;
            CurrentState cs = new CurrentState();
            world = cs.loading();
            player = world.getPlayer();
        }

        while (p < input.length()) {
            char action = lcInput.charAt(p);
            if (action == 'q') {
                //save game
                CurrentState cs = new CurrentState();
                cs.save(world);
                //close game
                break;
            }
            //take action
            /** this part of logic needs to be modified for player status update in the worldclass*/
            player.Move(action);
            p += 1;
        }
        world.updatePlayer();
        finalWorldFrame = world.getWorld();


        return finalWorldFrame;
    }
}

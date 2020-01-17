package byog.Core;

import byog.TileEngine.TETile;

import java.io.*;

public class CurrentState implements Serializable {
    private World currentWorld;

    public CurrentState(){

    }

    public CurrentState(World cw){
        currentWorld = cw;
    }
    public World loading(){
        //this function is used to load the saved game
        try {

            FileInputStream fis = new FileInputStream("/log/world.zqy");
            ObjectInputStream ois = new ObjectInputStream(fis);
            CurrentState savegame = (CurrentState) ois.readObject();
            ois.close();
            return savegame.currentWorld;

        }catch (FileNotFoundException e) {
            System.out.println("Game never save");
            System.exit(0);

        }catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;



    }

    public void save(World cw){
        //this function is used to save the current state of the game
        File Dir = new File("/log");
        if (!Dir.exists()) {
            Dir.mkdir();
        }
        File saveFile = new File("/log/world.zqy");
        try{
            if (! saveFile.exists()){
                saveFile.createNewFile();
            }
            //write object to file /world.zqy
            CurrentState CS = new CurrentState(cw);
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(CS);
            oos.close();


        }catch (FileNotFoundException e) {
            System.out.println("Game save failed!");
            System.exit(0);
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}

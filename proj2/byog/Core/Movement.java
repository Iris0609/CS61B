package byog.Core;

public interface Movement {

    void Move(char action);
    boolean Blocked(int x, int y);

}

import java.util.ArrayList;

public abstract class Game {
    public abstract State initGame();
    public abstract ArrayList<Tile[]> actions(State s);
    public abstract int utility(State s);
    public abstract State result(State s, Tile[] a);
    public abstract boolean isTerminal(State s);
}

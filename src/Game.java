import java.util.ArrayList;

public abstract class Game {
    public abstract ArrayList<Tile[]> Actions(State s);
    public abstract int Utility(State s, boolean maxTurn);
    public abstract State Result(State s, ArrayList<int[]> a);
    public abstract boolean isTerminal(State s);
}

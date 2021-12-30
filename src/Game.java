import java.util.ArrayList;

public abstract class Game {
    public abstract ArrayList<Tile[]> actions(State s);
    public abstract int utility(State s, boolean maxTurn);
    public abstract State result(State s, ArrayList<Tile[]> a);
    public abstract boolean isTerminal(State s);
}

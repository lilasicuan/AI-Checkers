import java.util.ArrayList;

public abstract class Game {
    public abstract State initGame();
    public abstract ArrayList<ArrayList<Action>> actions(State s);
    public abstract int utility(State s);
    public abstract State result(State s, ArrayList<Tile> actionSequence);
    public abstract boolean isTerminal(State s);
}

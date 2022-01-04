import java.util.ArrayList;

public abstract class Game {
    public abstract State initGame();
    public abstract ArrayList<ActionSequence> actions(State s);
    public abstract int utility(State s);
    public abstract State result(State s, ActionSequence actionSequence);
    public abstract boolean isTerminal(State s);
}

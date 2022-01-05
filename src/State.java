import java.util.ArrayList;

/**
 * Represents a node in a Game Tree
 */
public class State {
    private boolean maxTurn;
    private Board boardConfig;
    private int score;

    private State parent;
    private ArrayList<State> children;

    public State(Board board, boolean isMaxTurn, State parent) {
        boardConfig = board;
        maxTurn = isMaxTurn;
        score = -1000;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public boolean isMaxTurn() {
        return maxTurn;
    }

    public Board getBoard() {
        return boardConfig;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public State getParent() {
        return parent;
    }

    public ArrayList<State> getChildren() {
        return children;
    }

    public void addChild(State child) {
        children.add(child);
    }
}
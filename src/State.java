/**
 * Represents the configuration of the board
 */

public class State {
    private boolean maxTurn;
    private Board boardConfig;
    private int value;

    public State(Board board, boolean isMaxTurn) {
        boardConfig = board;
        maxTurn = isMaxTurn;
        value = 0;
    }

    public boolean isMaxTurn() {
        return maxTurn;
    }

    public Board getBoard() {
        return boardConfig;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
    }
}
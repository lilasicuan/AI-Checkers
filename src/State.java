/**
 * Represents the configuration of the board
 */

public class State {
    // is the max player GOING to move at this state?
    private boolean maxTurn;
    private Board boardConfig;
    // list of payoffs from State descendents

    public State(Board board, boolean isMaxTurn) {
        boardConfig = board;
        maxTurn = isMaxTurn;
    }

    public boolean isMaxTurn() {
        return maxTurn;
    }

    public Board getBoard() {
        return boardConfig;
    }
}
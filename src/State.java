public class State {
    private boolean maxTurn;
    private Board boardConfig;

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
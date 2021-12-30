import java.util.ArrayList;

/**
 * Will hold Checkers version of:
 * - Actions(state) : action[]
 * - Result(state, action) : state
 * - Utility(state, player) : int
 * - isTerminal(state) : boolean
 * 
 * Will generate the initial state of the game
 */

public class Checkers extends Game {
    public Board board;

    public Checkers() {
        board = new Board();
    }

    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(board, true);
        return init;
    }

    public ArrayList<Tile[]> Actions(State s) {
        // Each Tile array is the origin tile and destination tile
        ArrayList<Tile[]> actions = new ArrayList<>();
        /**
         * For every current turn piece,
         * get all actions that can be performed by the piece
         */
    
         return actions;
    }

    public int Utility(State s, boolean maxTurn) {
        return 1;
    }

    public State Result(State s, ArrayList<int[]> a) {
        return new State(board, true);
    }

    public boolean isTerminal(State s) {
        return true;
    }
    
}


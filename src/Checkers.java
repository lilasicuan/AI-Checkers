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
    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(new Board(), true);
        return init;
    }

    public ArrayList<Tile[]> actions(State s) {
        // Each Tile array is the origin tile and destination tile
        ArrayList<Tile[]> actions = new ArrayList<>();
        /**
         * For every current turn piece,
         * get all actions that can be performed by the piece
         */

        Board currBoard = s.getBoard();

        // for()

        return actions;
    }

    public int utility(State s, boolean maxTurn) {
        return 1;
    }

    public State result(State s, ArrayList<Tile[]> a) {
        return new State(new Board(), true);
    }

    public boolean isTerminal(State s) {
        return actions(s).isEmpty();
    }
    
}


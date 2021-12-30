import java.util.ArrayList;

/**
 * Will hold Checkers version of:
 * - Actions(state) : action[]
 * - Result(state, action) : state
 * - Utility(state, player) : int
 * 
 * Will generate the initial state of the game
 */

public class Checkers {
    // Will generate the initial state of the game
    public static State initGame() {
        State init = new State();

        return init;
    }

    public ArrayList<Tile[]> actions(State s) {
        // Each Tile array is the origin tile and destination tile
        ArrayList<Tile[]> actions = new ArrayList<>();
        /**
         * For every current turn piece,
         * get all actions that can be performed by the piece
         */
    
         return actions;
    }
}


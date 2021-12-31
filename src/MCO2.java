import java.util.ArrayList;

public class MCO2 {
    public static void main(String[] args) {
        // State initialState = Checkers.initGame();
        // Minimax.minimax(initialState);

        // Debugging
        Checkers checkers = new Checkers();
        State init = checkers.initGame();
        ArrayList<Tile[]> actions = checkers.actions(init);

        checkers.result(init, actions.get(6)).getBoard().displayBoard();

        //Expected 7 actions upon init
        // checkers.displayAllActions(actions);
    }

}
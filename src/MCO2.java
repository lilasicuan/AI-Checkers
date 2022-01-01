import java.util.ArrayList;

public class MCO2 {
    public static void main(String[] args) {
        // State initialState = Checkers.initGame();
        // Minimax.minimax(initialState);

        // Debugging
        Checkers checkers = new Checkers();
        State init = checkers.initGame();
        ArrayList<ArrayList<Tile>> actions = checkers.actions(init);
        ArrayList<Tile> sequence = new ArrayList<>();

        sequence.add(new Tile(2, 7));
        sequence.add(new Tile(7, 2));

        // checkers.result(init, sequence).getBoard().displayBoard();
        // init.getBoard().displayBoard();

        //Expected 7 actions upon init
        checkers.displayAllActions(actions);
    }

}
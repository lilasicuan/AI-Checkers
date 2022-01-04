import java.util.ArrayList;

public class MCO2 {
    public static void main(String[] args) {
        // Debugging
        Checkers checkers = new Checkers();
        State init = checkers.initGame();
        init.getBoard().displayBoard();

        ArrayList<ActionSequence> actions = checkers.actions(init);
        checkers.result(init, actions.get(0));
    }

}
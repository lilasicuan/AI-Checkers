import java.util.*;
import java.io.*;

public class MCO2 {
    public static void main(String[] args) {
        // Debugging
        Checkers checkers = new Checkers();
        State state = checkers.initGame();
        Minimax mm = new Minimax();

        mm.minimax(state, checkers, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
    }

    // Implement continuous capturing for human player
    public static State userResponse(Game game, State s) {
        Scanner sc = new Scanner(System.in);

        int originRow;
        int originCol;
        int destRow;
        int destCol;

        Action action = new Action();
        ArrayList<Action> actions = new ArrayList<>();
        
        System.out.print("\nEnter row and column of target PIECE\n>> ");
        originRow = sc.nextInt();
        originCol = sc.nextInt();

        System.out.print("\nEnter row and column of target TILE\n>> ");
        destRow = sc.nextInt();
        destCol = sc.nextInt();
        sc.close();

        action.newMove(s.getBoard().getTile(originRow, originCol).getOccupant(), s.getBoard().getTile(originRow, originCol), s.getBoard().getTile(destRow, destCol));
        actions.add(action);
        ActionSequence sequence = new ActionSequence(actions);
        return game.result(s, sequence);
    }

}
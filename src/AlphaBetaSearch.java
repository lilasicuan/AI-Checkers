/**
 * Will hold the following main methods:
 * - minimax(state) :
 * - player(state) : boolean
 */
import java.util.ArrayList;

// Based on the alpha-beta search algorithm of figure 5.7 of AIMA
public class AlphaBetaSearch {

    public ArrayList<Action> alphaBetaSearch(State state, Game game) {
        int v = maxValue(state, game, Integer.MIN_VALUE, Integer.MAX_VALUE);
        ArrayList<ArrayList<Action>> actions = game.actions(state);
        for(ArrayList<Action> a : actions) {
            if(game.result(state, a).getValue() == v)
                return a;
        }
        return null;
    }

    public int maxValue(State state, Game game, int alpha, int beta) {
        if(game.isTerminal(state))
            return game.utility(state);

        int v = Integer.MIN_VALUE;
        ArrayList<ArrayList<Action>> actions = game.actions(state);
        for(ArrayList<Action> a : actions) {
            v = Math.max(v, minValue(game.result(state, a), game, alpha, beta));
            if(v >= beta)
                return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }

    public int minValue(State state, Game game, int alpha, int beta) {
        if(game.isTerminal(state))
            return game.utility(state);

        int v = Integer.MAX_VALUE;
        ArrayList<ArrayList<Action>> actions = game.actions(state);
        for(ArrayList<Action> a : actions) {
            v = Math.min(v, maxValue(game.result(state, a), game, alpha, beta));
            if(v <= alpha)
                return v;
            beta = Math.min(beta, v);
        }
        return v;
    }

    public boolean player(State s) {
        return s.isMaxTurn();
    }

}
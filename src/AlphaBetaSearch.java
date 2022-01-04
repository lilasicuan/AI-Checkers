import java.util.ArrayList;

// Based on the alpha-beta search algorithm of figure 5.7 of AIMA
public class AlphaBetaSearch {

    public ActionSequence alphaBetaSearch(State state, Game game) {
        int v = maxValue(state, game, Integer.MIN_VALUE, Integer.MAX_VALUE);
        ArrayList<ActionSequence> actions = game.actions(state);
        for(ActionSequence a : actions) {
            if(a.getOrderingValue() == v)
                return a;
        }

        return null;
    }

    public int maxValue(State state, Game game, int alpha, int beta) {
        if(game.isTerminal(state))
            return game.utility(state);

        int v = Integer.MIN_VALUE;
        ArrayList<ActionSequence> actions = game.actions(state);
        for(ActionSequence a : actions) {
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
        ArrayList<ActionSequence> actions = game.actions(state);
        for(ActionSequence a : actions) {
            v = Math.min(v, maxValue(game.result(state, a), game, alpha, beta));
            if(v <= alpha)
                return v;
            beta = Math.min(beta, v);
        }

        return v;
    }

    public boolean playerMax(State s) {
        return s.isMaxTurn();
    }

    // Has to order action sequences based on ordering value and not just size
    // How to assign these values tho.
    public void ordering(ArrayList<ActionSequence> actions, boolean isMax) {
        if(isMax)
            actions.sort((a1, a2) -> a2.getOrderingValue().compareTo(a1.getOrderingValue()));
        else
            actions.sort((a1, a2) -> a1.getOrderingValue().compareTo(a2.getOrderingValue()));
    }

}
import java.util.ArrayList;

public class Minimax {
    public int minimax(State state, Game game, int alpha, int beta) {
        if(game.isTerminal(state)) {
            int utilScore = game.utility(state);
            state.setScore(utilScore);
            return utilScore;
        }

        ArrayList<ActionSequence> sequences = game.actions(state);

        if(state.isMaxTurn()) {
            int bestVal = Integer.MIN_VALUE;
            for(ActionSequence a : sequences) {
                State resultState = game.result(state, a);
                bestVal = Math.max(bestVal, minimax(resultState, game, alpha, beta));
                if(bestVal >= beta) {
                    state.setScore(bestVal);
                    return bestVal;
                }
                alpha = Math.max(alpha, bestVal);
                if(beta <= alpha) //idk about this I just saw on GFG
                    break;
            }
            state.setScore(bestVal);
            return bestVal;
        }

        else {
            int bestVal = Integer.MAX_VALUE;
            for(ActionSequence a : sequences) {
                bestVal = Math.min(bestVal, minimax(game.result(state, a), game, alpha, beta));
                state.setScore(bestVal);
                if(bestVal <= alpha) {
                    state.setScore(bestVal);
                    return bestVal;
                }
                beta = Math.min(beta, bestVal);
                if(beta <= alpha) //idk about this I just saw on GFG
                    break;
            }
            state.setScore(bestVal);
            return bestVal;
        }
    }

}

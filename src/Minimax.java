/**
 * Will hold the following main methods:
 * - minimax(state) :
 * - player(state) : boolean
 */

public class Minimax {
    public static int minimax(State s, Game game) {
        if(game.isTerminal(s))
            return game.Utility(s, s.isMaxTurn());
        
        int utility = 0;
        return utility;
    }
}
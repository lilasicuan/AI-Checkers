/**
 * Will hold the following main methods:
 * - minimax(state) :
 * - player(state) : boolean
 */

public class Minimax {
    public int minimax(State s, Game game) {
        // if(game.isTerminal(s))
        //     return game.utility(s, s.isMaxTurn()); //2nd param doesnt look right
        // else if(player(s)) {

        // }

        int utility = 0;
        return utility;
    }

    public boolean player(State s) {
        return s.isMaxTurn();
    }
}
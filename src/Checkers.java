import java.util.ArrayList;

/**
 * Will hold Checkers version of:
 * - Actions(state) : action[]
 * - Result(state, action) : state
 * - Utility(state, player) : int
 * - isTerminal(state) : boolean
 * 
 * Will generate the initial state of the game
 */

public class Checkers extends Game {
    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(new Board(), true);
        return init;
    }

    public ArrayList<Tile[]> actions(State s) {
        // Each Tile array is the origin tile and destination tile
        ArrayList<Tile[]> actions = new ArrayList<>();

        Board currBoard = s.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Tile currTile = currBoard.getTile(i, j);
                // If tile is occupied and if occupant is one of current player's pieces
                if(currTile.isOccupied() && currTile.getOccupant().isMaxPiece() == s.isMaxTurn())
                    actions.addAll(getValidMoves(currTile, currTile.getOccupant().isMaxPiece(), s.getBoard()));
            }
        }

        return actions;
    }

    public int utility(State s, boolean maxTurn) {
        return 1;
    }

    public State result(State s, ArrayList<Tile[]> a) {
        return new State(new Board(), true);
    }

    public boolean isTerminal(State s) {
        return actions(s).isEmpty();
    }

    private Tile isValidDest(Tile origin, String direction, Board board, int dist) {
        int newRow;
        int newCol;
        switch(direction) {
            case "SW":
                newRow = origin.getRow() + dist;
                newCol = origin.getCol() - dist;
                break;
            case "SE":
                newRow = origin.getRow() + dist;
                newCol = origin.getCol() + dist;
                break;
            case "NW":
                newRow = origin.getRow() - dist;
                newCol = origin.getCol() - dist;
            default:
                newRow = origin.getRow() - dist;
                newCol = origin.getCol() + dist;
        }
        if((newRow < 8 && newRow >= 0) && (newCol >= 0 && newCol < 8))
            if(!board.getTile(newRow, newCol).isOccupied())
                return board.getTile(newRow, newCol);
            else
                if(dist < 2)
                    return isValidDest(origin, direction, board, 2);
        return null;
    }

    private ArrayList<Tile[]> getValidMoves(Tile tile, boolean isKing, Board board) {
        ArrayList<Tile[]> pieceMoves = new ArrayList<>();
        Tile[] currMove = new Tile[2];
        Piece occupant = tile.getOccupant();

        String[] directions = {"SW", "SE", "NW", "NE"};

        // Will check directions 0 and 1
        int i = 0;
        int high = 2;

        // will check directions 2 and 3
        if(!occupant.isMaxPiece() || occupant.isKingPiece()) {
            i = 2;
            high = 4;
        }

        // Will check all directions
        if(occupant.isKingPiece()) {
            i = 0;
            high = 4;
        }

        for(; i < high; i++) {
            Tile dest = isValidDest(tile, directions[i], board, 1);
            if(dest != null) {
                currMove[0] = tile;
                currMove[1] = dest;
                pieceMoves.add(currMove);
            }
        }

        return pieceMoves;
    }

    // Debugging
    public void displayActions() {
        
    }
    
}


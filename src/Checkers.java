import java.util.ArrayList;

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

    public int utility(State s) {
        Board board = s.getBoard();
        int minPieces = 0;
        int maxPieces = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board.getTile(i, j).isBlack() && board.getTile(i, j).isOccupied()) {
                    if(board.getTile(i, j).getOccupant().isMaxPiece())
                        maxPieces++;
                    else
                        minPieces++;
                }
            }
        }

        if(minPieces == 0)
            return 1;
        if(maxPieces == 0)
            return -1;

        return 0; // Default if draw
    }

    public State result(State s, Tile[] action) {
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
                if(dist < 2 && board.getTile(newRow, newCol).getOccupant().isMaxPiece() != origin.getOccupant().isMaxPiece()) // if diag piece is not current's piece
                    return isValidDest(origin, direction, board, 2);
        return null;
    }

    private ArrayList<Tile[]> getValidMoves(Tile tile, boolean isKing, Board board) {
        ArrayList<Tile[]> pieceMoves = new ArrayList<>();
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
                Tile[] currMove = new Tile[2];
                currMove[0] = tile;
                currMove[1] = dest;
                pieceMoves.add(currMove);
            }
        }

        return pieceMoves;
    }

    // Debugging
    public void displayAction(Tile[] action) {
        System.out.println("[" + action[0].getRow() + ", " + action[0].getCol() + "] to " + "[" + action[1].getRow() + ", " + action[1].getCol() + "]");
    }

    public void displayAllActions(ArrayList<Tile[]> actions) {
        for(int i = 0; i < actions.size(); i++)
            displayAction(actions.get(i));
    }
    
}


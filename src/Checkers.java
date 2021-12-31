import java.util.ArrayList;

public class Checkers extends Game {
    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(new Board(true), true);
        return init;
    }

    public ArrayList<ArrayList<Tile>> actions(State s) {
        // Each Tile array is the origin tile and destination tile
        ArrayList<ArrayList<Tile>> actions = new ArrayList<>();

        Board currBoard = s.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Tile currTile = currBoard.getTile(i, j);
                // If tile is occupied and if occupant is one of current player's pieces
                if(currTile.isOccupied() && currTile.getOccupant().isMaxPiece() == s.isMaxTurn()) {
                    Board boardCopy = s.getBoard();
                    actions.addAll(getValidMoves(boardCopy.getTile(i, j), currTile.getOccupant().isMaxPiece(), boardCopy, new ArrayList<ArrayList<Tile>>()));
                }
            }
        }

        return actions;
    }

    // WILL UPDATE
    public int utility(State s) {
        Board board = s.getBoard();
        int minPieces = 0;
        int maxPieces = 0;

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(board.getTile(i, j).isBlack() && board.getTile(i, j).isOccupied()) {
                    if(board.getTile(i, j).getOccupant().isMaxPiece())
                        maxPieces++;
                    else
                        minPieces++;
                }


        if(minPieces == 0)
            return 1;
        if(maxPieces == 0)
            return -1;

        return 0; // Default if draw
    }

    public State result(State s, ArrayList<Tile> actionSequence) {
        Board newBoard = s.getBoard();

        for(int i = 0; i < actionSequence.size(); i++) {
            if(i < actionSequence.size() - 1) {
                Tile origin = newBoard.getTile(actionSequence.get(i).getRow(), actionSequence.get(i).getCol());
                Tile dest = newBoard.getTile(actionSequence.get(i + 1).getRow(), actionSequence.get(i + 1).getCol());

                Tile capture = captureMade(newBoard, origin, dest);
                if(capture != null) // if a capture was made, removes captured piece from board
                    capture.toggleOccupant(null);
                dest.toggleOccupant(origin.getOccupant());
                origin.toggleOccupant(null);
            }
            
        }

        State result = new State(newBoard, !s.isMaxTurn());
        return result;
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
        if((newRow < 8 && newRow >= 0) && (newCol >= 0 && newCol < 8)) { // If destination is within bounds
            if(!board.getTile(newRow, newCol).isOccupied())
                return board.getTile(newRow, newCol);
            else
                if(dist < 2 && board.getTile(newRow, newCol).getOccupant().isMaxPiece() != origin.getOccupant().isMaxPiece()) // if diag piece is not current's piece
                    return isValidDest(origin, direction, board, 2);
        }
        return null;
    }

    // Need to simulate capturing for multi-capture moves
    private ArrayList<ArrayList<Tile>> getValidMoves(Tile origin, boolean isKing, Board board, ArrayList<ArrayList<Tile>> pieceMoves) {
        Piece occupant = origin.getOccupant();
        String[] directions = {"SW", "SE", "NW", "NE"};

        // Will check directions 0 and 1
        int i = 0;
        int high = 2;

        // will check directions 2 and 3
        if(!occupant.isMaxPiece()) {
            i = 2;
            high = 4;
        }

        // Will check all directions
        if(occupant.isKingPiece()) {
            i = 0;
            high = 4;
        }

        // Fix adding to pieceMoves and actionSequence
        for(; i < high; i++) {
            Tile dest = isValidDest(origin, directions[i], board, 1);
            if(dest != null) { // Destination is valid
                ArrayList<Tile> moveSequence = new ArrayList<>(); // For multi-capture moves
                Tile captured = captureMade(board, origin, dest);

                // Need to disregard all previous items in pieceMoves and add only capture actionSequences
                if(captured != null) { // If capture made
                    ArrayList<Tile> checkMove = new ArrayList<>();
                    checkMove.add(origin);
                    checkMove.add(dest);
                    board = result(new State(board, isKing), checkMove).getBoard();
                    // board.displayBoard();
                    for(int j = 0; j < pieceMoves.size(); j++)
                        if(pieceMoves.get(j).size() < 3)
                            pieceMoves.remove(j);
                    pieceMoves.addAll(getValidMoves(dest, isKing, board, pieceMoves));
                }

                moveSequence.add(origin);
                moveSequence.add(dest);
                // currMove[1] = dest;
                pieceMoves.add(moveSequence);
                
            }
        }

        return pieceMoves;
    }

    private Tile captureMade(Board board, Tile origin, Tile dest) {
        // If a capture was done
        if(Math.abs(origin.getRow() - dest.getRow()) > 1) {
            int capturedRow = Math.max(origin.getRow(), dest.getRow()) - 1;
            int capturedCol = Math.max(origin.getCol(), dest.getCol()) - 1;

            return board.getTile(capturedRow, capturedCol);
        }
            
        return null;
    }

    // Debugging
    public void displayAction(ArrayList<Tile> action) {
        for(int i = 0; i < action.size(); i++) {
            System.out.print("[" + action.get(i).getRow() + ", " + action.get(i).getCol() + "]");
            if(i < action.size() - 1)
                System.out.print(" to ");
        }
        System.out.println();
        
    }

    public void displayAllActions(ArrayList<ArrayList<Tile>> actions) {
        for(int i = 0; i < actions.size(); i++)
            displayAction(actions.get(i));
    }
    
}


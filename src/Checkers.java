import java.util.ArrayList;

public class Checkers extends Game {
    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(new Board(true), true);
        return init;
    }

    public ArrayList<ArrayList<Action>> actions(State s) {
        ArrayList<ArrayList<Action>> actions = new ArrayList<>();
        Board currBoard = s.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Tile currTile = currBoard.getTile(i, j);
                // If tile is occupied and if occupant is one of current player's pieces
                if(currTile.isOccupied() && currTile.getOccupant().isMaxPiece() == s.isMaxTurn())
                    actions.addAll(getValidMoves(currTile, currBoard, new ArrayList<>(), false));
            }
        }

        boolean hasCapture = false;
        for(int i = 0; i < actions.size(); i++)
            if(actions.get(i).size() > 1) {
                hasCapture = true;
                break;
            }
        
        if(hasCapture)
            actions.removeIf(action -> action.size() < 2);

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

    public State result(State s, ArrayList<Action> actionSequence) {
        Board newBoard = s.getBoard();

        for(int i = 0; i < actionSequence.size(); i++) {
            newBoard.doMove(actionSequence.get(i));
            // newBoard.displayBoard();
        }

        State result = new State(newBoard, !s.isMaxTurn());
        return result;
    }

    public boolean isTerminal(State s) {
        return actions(s).isEmpty();
    }

    private boolean isOutOfBounds(int x, int y) {
        if (x >= 8 || y >= 8 || x <= 0 || y <= 0){
            return true;
        }
        return false;
    }

    private int[][] getDir(Tile tile) {
        int[][] isMaxDir = {{-1, 1}, {1, 1}};
        int[][] isNotMaxDir = {{-1, -1}, {1, -1}};
        int[][] isKingDir = {{-1, 1}, {1, 1}, {-1, -1}, {1, -1}};

        int[][] currentDir;
        boolean isKing = tile.getOccupant().isKingPiece();
        boolean isMax = tile.getOccupant().isMaxPiece();
        if (isKing)
            currentDir = isKingDir;
        else if (isMax)
            currentDir = isMaxDir;
        else
            currentDir = isNotMaxDir;

        return currentDir;
    }

    private boolean expandActionSequence(Tile tile, Board board, ArrayList<Action> actionSequence, ArrayList<ArrayList<Action>> allMoves) {
        ArrayList<int[]> attackMoves = getAttackMoves(tile, board);
        boolean hasMoves = false;
        if (attackMoves.size() != 0) {
            hasMoves = true;
            for (int[] dir: attackMoves){
                ArrayList<Action> localActionSequence = new ArrayList<>(actionSequence);
                Tile captureTile = board.getTile(tile.getRow() + dir[1], tile.getCol() + dir[0]);
                Tile landingTile = board.getTile(tile.getRow() + dir[1] * 2, tile.getCol() + dir[0] * 2);
                Action attackAction = new Action();
                attackAction.newAttack(tile.getOccupant(), tile, landingTile, captureTile.getOccupant());
                localActionSequence.add(attackAction);
                board.doMove(attackAction);
                expandActionSequence(landingTile, board, localActionSequence, allMoves);
                board.undoMove();
                
            }
        }
        if (!hasMoves) { // Terminal Node
            if (!actionSequence.isEmpty()){
                allMoves.add(actionSequence);
            }
            return false;
        }
        return true;
    }

    private ArrayList<int[]> getAttackMoves(Tile tile, Board board) {
        int[][] possibleDirections = getDir(tile);
        ArrayList<int[]> attackMoves = new ArrayList<>();
        
        for (int[] dir : possibleDirections) {
            int tempDestX = tile.getCol() + dir[0];
            int tempDestY = tile.getRow() + dir[1];

            if(!isOutOfBounds(tempDestY, tempDestX)) {
                Tile destinationTile = board.getTile(tempDestY, tempDestX);
                if(destinationTile.isOccupied())  
                    if(destinationTile.getOccupant().isMaxPiece() != tile.getOccupant().isMaxPiece())
                        attackMoves.add(dir);
            }
                       
        }
        return attackMoves;
    }

    private ArrayList<ArrayList<Action>> getValidMoves(Tile origin, Board board, ArrayList<ArrayList<Action>> allMoves, boolean isAttackSequence) {
        if (!expandActionSequence(origin, board, new ArrayList<Action>(), allMoves)) {
            int[][] directions = getDir(origin);
            for (int[] dir : directions) {
                ArrayList<Action> actionSequence = new ArrayList<>();
                int tempDestX = origin.getCol() + dir[0];
                int tempDestY = origin.getRow() + dir[1];

                if(!isOutOfBounds(tempDestY, tempDestX)) {
                    Tile destinationTile = board.getTile(tempDestY, tempDestX);
                    if(!destinationTile.isOccupied()) {
                        Action newAction = new Action();
                        newAction.newMove(origin.getOccupant(), origin, destinationTile);
                        actionSequence.add(newAction);
                        allMoves.add(actionSequence);
                    }
                }
            }
        }
        
        return allMoves;
    }

    // Debugging
    public void displayAction(ArrayList<Action> actions) {
        for (Action action: actions) {
            action.display();
        }
        System.out.println("=== End Of Action Sequence");
        
    }

    public void displayAllActions(ArrayList<ArrayList<Action>> actions) {
        System.out.println("DISPLAYING");
        for (ArrayList<Action> action : actions){
            displayAction(action);
        }
    }
    
}


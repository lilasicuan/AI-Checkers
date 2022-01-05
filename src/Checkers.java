import java.util.ArrayList;

public class Checkers extends Game {
    // Will generate the initial state of the game
    public State initGame() {
        State init = new State(new Board(false), true, null);
        return init;
    }

    public ArrayList<ActionSequence> actions(State s) {
        ArrayList<ActionSequence> actions = new ArrayList<>();
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

        for(ActionSequence a : actions) {
            if(a.getActionSequence().get(0).isCapture())
                hasCapture = true;
            computeOrdering(a);
        }
        
        if(hasCapture)
            actions.removeIf(action -> !action.getActionSequence().get(0).isCapture());
        
        ordering(actions, false);

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

        return maxPieces - minPieces;
    }

    public State result(State s, ActionSequence actionSequence) {
        Board newBoard = s.getBoard();

        for(Action action : actionSequence.getActionSequence()) {
            newBoard.doMove(action);
            Tile destination = action.getDestination();
            if(destination.isMaxEdge() != null && destination.isMaxEdge() != destination.getOccupant().isMaxPiece())
                destination.getOccupant().setKing();
        }

        State result = new State(newBoard, !s.isMaxTurn(), s);
        s.addChild(result);
        return result;
    }

    public boolean isTerminal(State s) {
        int maxPieces = 0;
        int minPieces = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(s.getBoard().getTile(i, j).isOccupied())
                    if(s.getBoard().getTile(i, j).getOccupant().isMaxPiece())
                        maxPieces++;
                    else
                        minPieces++;
            }
        }

        if(maxPieces == 0 || minPieces == 0)
            return true;

        return actions(s).isEmpty();
    }

    private boolean isOutOfBounds(int x, int y) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0){
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

    private boolean expandActionSequence(Tile tile, Board board, ArrayList<Action> actionSequence, ArrayList<ActionSequence> allMoves) {
        ArrayList<int[]> attackMoves = getAttackMoves(tile, board);
        boolean hasMoves = false;

        if (attackMoves.size() != 0) {
            hasMoves = true;
            for (int[] dir: attackMoves) {
                ArrayList<Action> localActionSequence = new ArrayList<>(actionSequence);
                Tile captureTile = board.getTile(tile.getRow() + dir[1], tile.getCol() + dir[0]);
                if (isOutOfBounds(tile.getRow() + dir[1] * 2, tile.getCol() + dir[0] * 2)) {continue;}
                Tile landingTile = board.getTile(tile.getRow() + dir[1] * 2, tile.getCol() + dir[0] * 2);
                Action attackAction = new Action();
                attackAction.newAttack(tile.getOccupant(), tile, landingTile, captureTile.getOccupant());
                localActionSequence.add(attackAction);
                board.doMove(attackAction);
                expandActionSequence(landingTile, board, localActionSequence, allMoves);
                board.undoMove();
                
            }
        }
        if(!hasMoves) { // Terminal Node
            if (!actionSequence.isEmpty())
                allMoves.add(new ActionSequence(actionSequence));
            return false;
        }
        return true;
    }

    // Returns an array of direction values that have captures
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

    private ArrayList<ActionSequence> getValidMoves(Tile origin, Board board, ArrayList<ActionSequence> allMoves, boolean isAttackSequence) {
        if(!expandActionSequence(origin, board, new ArrayList<Action>(), allMoves)) { // If there are no possible capture moves
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
                        allMoves.add(new ActionSequence(actionSequence));
                    }
                }
            }
        }
        
        return allMoves;
    }

    private void computeOrdering(ActionSequence sequence) {
        int value = 0;

        for(Action a : sequence.getActionSequence()) {
            Tile origin = sequence.getActionSequence().get(0).getOrigin();
            Tile lastDest = sequence.getActionSequence().get(sequence.getActionSequence().size() - 1).getDestination();
            if(lastDest.isMaxEdge() != null && lastDest.isMaxEdge() != origin.getOccupant().isMaxPiece())
                value += 10;
            if(a.isCapture())
                value += 10;
            if(a.getDestination().getCol() == 0 || a.getDestination().getCol() == 7)
                value += 5;
        }

        sequence.setOrderingValue(value);
    }

    private void ordering(ArrayList<ActionSequence> sequence, boolean withOrdering) {
        if(withOrdering) {
            boolean isMax = sequence.get(0).getActionSequence().get(0).getPiece().isMaxPiece();
            if(isMax)
                sequence.sort((a1, a2) -> a2.getOrderingValue().compareTo(a1.getOrderingValue()));
            else
                sequence.sort((a1, a2) -> a1.getOrderingValue().compareTo(a2.getOrderingValue()));
        }
        
    }

    // Debugging
    public void displayAction(ActionSequence actions) {
        for (Action action: actions.getActionSequence()) {
            action.display();
        }
        System.out.println("=== End Of Action Sequence");
        
    }

    public void displayAllActions(ArrayList<ActionSequence> actions) {
        System.out.println("DISPLAYING");
        for (ActionSequence action : actions){
            displayAction(action);
        }
    }
    
}


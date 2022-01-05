import java.util.ArrayList;

public class Board {

    private Tile[][] tiles;
    public ArrayList<Action> moveList = new ArrayList<>();

    public Board(boolean debug) {
        tiles = new Tile[8][8];
        if(debug)
            initDebug();
        else
            initBoard();
    }

    public void initBoard() {
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j);
                if((i % 2 == 0) != (j % 2 == 0))
                    tiles[i][j].setBlack();
                if(i == 0)
                    tiles[i][j].setMaxEdge(true);
                else if(i == 7)
                    tiles[i][j].setMaxEdge(false);
            }
        
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 8; j++)
                if(tiles[i][j].isBlack())
                    tiles[i][j].toggleOccupant(new Piece(true, i, j));

        for(int i = 5; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(tiles[i][j].isBlack())
                    tiles[i][j].toggleOccupant(new Piece(false, i, j));
    }

    public void initDebug() {
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j);
                if((i % 2 == 0) != (j % 2 == 0))
                    tiles[i][j].setBlack();
                if(i == 0)
                    tiles[i][j].setMaxEdge(true);
                else if(i == 7)
                    tiles[i][j].setMaxEdge(false);
            }
        
        // Max player pieces in default positions
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 8; j++)
                if(tiles[i][j].isBlack())
                    tiles[i][j].toggleOccupant(new Piece(true, i, j));

        tiles[2][1].toggleOccupant(null);
        tiles[5][4].toggleOccupant(new Piece(true, 5, 4));
        tiles[6][5].toggleOccupant(new Piece(false, 6, 5));
    }

    public void doMove(Action action) {
        moveList.add(action);

        action.getPiece().setRow(action.getDestination().getRow());
        action.getPiece().setCol(action.getDestination().getCol());
        tiles[action.getOrigin().getRow()][action.getOrigin().getCol()].toggleOccupant(null);
        tiles[action.getDestination().getRow()][action.getDestination().getCol()].toggleOccupant(action.getPiece());

        if(action.isCapture())
            tiles[action.getCaptured().getRow()][action.getCaptured().getCol()].toggleOccupant(null);
    }


    public void undoMove() {
        Action action = moveList.get(moveList.size() - 1);
        moveList.remove(moveList.size() - 1);
        tiles[action.getOrigin().getRow()][action.getOrigin().getCol()].toggleOccupant(action.getPiece());
        tiles[action.getDestination().getRow()][action.getDestination().getCol()].toggleOccupant(null);

        if(action.isCapture())
            tiles[action.getCaptured().getRow()][action.getCaptured().getCol()].toggleOccupant(action.getCaptured());
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    // For debugging
    public void displayBoard() {
        System.out.println("---------------------------------");
        for(int i = 0; i < 8; i++) {
            System.out.print("|");
            for(int j = 0; j < 8; j++) {
                if(tiles[i][j].getOccupant() != null) {
                    if(tiles[i][j].getOccupant().isMaxPiece()){
                        if(tiles[i][j].getOccupant().isKingPiece())
                            System.out.print(" R |");
                        else
                            System.out.print(" r |");
                    }
                    else{
                        if(tiles[i][j].getOccupant().isKingPiece())
                            System.out.print(" B |");
                        else
                            System.out.print(" b |");
                    }
                }
                else
                    System.out.print("   |");
                if(j == 7)
                    System.out.println();
            }
            System.out.println("---------------------------------");
        }
    }
}

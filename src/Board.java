public class Board {
    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[8][8];
        initBoard();
    }

    public void initBoard() {
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile();
                if((i % 2 == 0) != (j % 2 == 0))
                    tiles[i][j].setBlack();
            }
        
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 8; j++)
                if(tiles[i][j].isBlack())
                    tiles[i][j].toggleOccupant(new Piece(true));

        for(int i = 5; i < 8; i++) {
            for(int j = 0; j < 8; j++)
                if(tiles[i][j].isBlack())
                    tiles[i][j].toggleOccupant(new Piece(false));
        }
    }

    // For debugging
    public void displayBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(tiles[i][j].getOccupant() != null) {
                    if(tiles[i][j].getOccupant().isMaxPiece()){
                        if(tiles[i][j].getOccupant().isKingPiece())
                            System.out.print('R');
                        else
                            System.out.print('r');
                    }
                    else{
                        if(tiles[i][j].getOccupant().isKingPiece())
                            System.out.print('B');
                        else
                            System.out.print('b');
                    }
                }
                else
                    System.out.print('-');
                if(j == 7)
                    System.out.println();
            }
        }
    }
}

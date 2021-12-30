public class Board {
    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[8][8];
        initBoard();
    }

    public void initBoard() {
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++) 
                tiles[i][j] = new Tile();
        
        for(int i = 0; i < 3; i++) {
            int j = 0;
            if(i % 2 == 0)
                j = 1;
            
            for(; j < 8; j += 2)
                tiles[i][j].toggleOccupant(new Piece(true));
        }

        for(int i = 5; i < 8; i++) {
            int j = 1;
            if(i % 2 == 0)
                j = 0;
            
            for(; j < 8; j += 2)
                tiles[i][j].toggleOccupant(new Piece(false));
        }
    }

    // For debugging
    public void displayBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(tiles[i][j].getOccupant() != null) {
                    if(tiles[i][j].getOccupant().isMaxPiece())
                        System.out.print('X');
                    else
                        System.out.print('O');
                }
                else
                    System.out.print('-');
                if(j == 7)
                    System.out.println();
            }
        }
    }
}

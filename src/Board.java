public class Board {
    private Tile[][] tiles;
    public Board() {

    }

    public void initBoard() {
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++) 
                tiles[i][j] = new Tile();
        
        
    }
}

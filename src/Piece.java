public class Piece {
    private boolean maxPiece;
    private boolean kingPiece;
    private int row;
    private int col;

    public Piece(boolean max, int row, int col) {
        this.row = row;
        this.col = col;
        maxPiece = max;
        kingPiece = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setKing() {
        kingPiece = true;
    }

    public boolean isKingPiece() {
        return kingPiece;
    }

    public boolean isMaxPiece() {
        return maxPiece;
    }
}
 
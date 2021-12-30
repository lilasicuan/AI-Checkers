public class Piece {
    private boolean maxPiece;
    private boolean kingPiece;

    public Piece(boolean max) {
        maxPiece = max;
        kingPiece = false;
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
 
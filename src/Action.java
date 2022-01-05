public class Action {
    private Piece piece;
    private Tile from;
    private Tile to;
    private Piece captured;
    private boolean isCaptureMove;
    private Integer orderingValue;

    public void newMove(Piece movedPiece, Tile origin, Tile destination) {
        isCaptureMove = false;
        piece = movedPiece;
        from = origin;
        to = destination;
        captured = null;
    }

    public void newAttack(Piece movedPiece, Tile fromTile, Tile toTile, Piece capturedPiece) {
        isCaptureMove = true;
        piece = movedPiece;
        from = fromTile;
        to = toTile;
        captured = capturedPiece;
    }

    public void display() {
        if(isCaptureMove)
            System.out.print("Capture>> ");
        else
            System.out.print("Move>>>>> ");
        System.out.print("FROM " + from.getRow() + ", " + from.getCol());
        System.out.println(" TO " + to.getRow() + ", " + to.getCol());
    }

    public boolean isCapture() {
        return isCaptureMove;
    }

    public Piece getPiece() {
        return piece;
    }

    public Tile getOrigin() {
        return from;
    }

    public Tile getDestination() {
        return to;
    }

    public Piece getCaptured() {
        return captured;
    }

    public Integer getOrderingValue() {
        return orderingValue;
    }

    public void setOrderingValue(int v) {
        orderingValue = v;
    }

}

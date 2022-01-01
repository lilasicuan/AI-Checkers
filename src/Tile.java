public class Tile {
    private Piece occupant;
    private boolean occupied;
    private boolean isBlack;
    private boolean maxEdge;
    private int row;
    private int col;

    // Each Tile starts off bare
    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        occupied = false;
        occupant = null;
        isBlack = false;
    }

    public void toggleOccupant(Piece occupant) {
        occupied = !occupied;
        if(occupant != null)
            setOccupant(occupant);
        else
            this.occupant = null;
    }

    public void setBlack() {
        isBlack = true;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isMaxEdge() {
        return maxEdge;
    }

    public void setMaxEdge(boolean max) {
        maxEdge = max;
    }

    private void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public Piece getOccupant() {
        return occupant;
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

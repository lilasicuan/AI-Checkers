public class Tile {
    private Piece occupant;
    private boolean occupied;
    private boolean isBlack;

    // Each Tile starts off bare
    public Tile() {
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

    private void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public Piece getOccupant() {
        return occupant;
    }
}

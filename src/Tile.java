public class Tile {
    private Piece occupant;
    private boolean occupied;

    // Each Tile starts off bare
    public Tile() {
        occupied = false;
    }

    public void toggleOccupant(Piece occupant) {
        occupied = !occupied;
        if(occupant != null)
            setOccupant(occupant);
        else
            this.occupant = null;
    }

    private void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public Piece getOccupant() {
        return occupant;
    }
}

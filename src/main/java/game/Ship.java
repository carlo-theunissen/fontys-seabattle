package game;

public class Ship {

    private int Length;
    private ShipType Type;

    public Ship(ShipType type){
        Type = type;
    }

    public int getLength() {
        switch (Type){
            case CRUISER:
                return 3;
            case SUBMARINE:
                return 3;
            case BATTLESHIP:
                return 4;
            case MINESWEEPER:
                return 2;
            case AIRCRAFTCARRIER:
                return 5;
            default:
                return 0;

        }
    }

    public Orientation getOrientation() {
        // TODO - implement Boat.getOrientation
        throw new UnsupportedOperationException();
    }

    public ShipStatus getStatus() {
        // TODO - implement Boat.getStatus
        throw new UnsupportedOperationException();
    }

}
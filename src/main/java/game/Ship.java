package game;

public class Ship {

    private int Length;
    private final ShipType Type;
    private Orientation orientation;
    private ShipStatus ShipStatus;
    private int X;
    private int Y;

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
        return orientation;
    }

    public ShipStatus getStatus() {
        return ShipStatus;
    }

    public void setX(int value){
        X = value;
    }

    public int getX(){
        return X;
    }

    public void setY(int value){
        Y = value;
    }

    public int getY(){
        return Y;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * used for hashset
     * @return
     */
    @Override
    public int hashCode() {
        return this.Type.toString().hashCode();
    }

}
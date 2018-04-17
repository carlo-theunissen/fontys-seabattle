package models;

public class Ship {

    private final ShipType type;
    private Orientation orientation;
    private ShipStatus shipStatus;
    private int X;
    private int Y;

    public Ship(ShipType type){
        if(type == null){
            throw new IllegalArgumentException();
        }
        this.shipStatus = ShipStatus.Invalid;
        this.type = type;
    }

    public int getLength() {
        switch (type){
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
        return shipStatus;
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
        if(orientation == null){
            throw new IllegalArgumentException();
        }
        this.orientation = orientation;
    }

    /**
     * used for hashset
     * @return
     */
    @Override
    public int hashCode() {
        return this.type.toString().hashCode();
    }

    public static String serialize(Ship ship){
        return ship.getX() +":"+ ship.getY() + ":" + ship.shipStatus.toString()+":"+ship.type.toString()+":"+(ship.orientation == Orientation.Vertical? 1 : 0);
    }
    public static Ship unserialize(String data) throws Exception {
        String[] split = data.split(":");
        Ship ship;
        if ("CRUISER".equals(split[3])) {
            ship = new Ship(ShipType.CRUISER);

        } else if ("SUBMARINE".equals(split[3])) {
            ship = new Ship(ShipType.SUBMARINE);

        } else if ("BATTLESHIP".equals(split[3])) {
            ship = new Ship(ShipType.BATTLESHIP);

        } else if ("MINESWEEPER".equals(split[3])) {
            ship = new Ship(ShipType.MINESWEEPER);

        } else if ("AIRCRAFTCARRIER".equals(split[3])) {
            ship = new Ship(ShipType.AIRCRAFTCARRIER);
        } else {
            throw new Exception("Type not found");
        }
        //	return new Hit(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2].equals("C") ? HitType.Collided : HitType.Miss);
        //

        ship.setX(Integer.parseInt(split[0]));
        ship.setY(Integer.parseInt(split[1]));

        if(split[2].equals("Alive")){
            ship.shipStatus = ShipStatus.Alive;
        } else if(split[2].equals("Dead")) {
            ship.shipStatus = ShipStatus.Dead;
        } else {
            ship.shipStatus = ShipStatus.Invalid;
        }

        ship.orientation = Orientation.values()[Integer.parseInt(split[4])];

        return ship;
    }

    public void setStatus(ShipStatus status) {
        this.shipStatus = status;
    }
}
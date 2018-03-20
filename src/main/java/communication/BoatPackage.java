package communication;

import models.Orientation;
import models.Ship;

public class BoatPackage extends CommunicationPackage {
    public BoatPackage(Ship ship) {
        super(CommunicationAction.PlaceBoat, serialize(ship));
    }
    private static String serialize(Ship ship){
        return ship.getOrientation() == Orientation.Horizontal ? "H":"V" + "|"+ship.getLength()+"|"+ship.getX()+"|"+ship.getY();
    }
}

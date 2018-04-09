package communication;

import models.Orientation;
import models.Ship;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoatPackage extends CommunicationPackage {
    public BoatPackage(Ship ship) {
        super(CommunicationAction.PlaceBoat, serialize(ship));
    }
    private static String serialize(Ship ship){
        return ship.getOrientation() == Orientation.Horizontal ? "H":"V" + "|"+ship.getLength()+"|"+ship.getX()+"|"+ship.getY();
    }
    public static Ship unserialize(String data){
        throw new NotImplementedException();
    }
}

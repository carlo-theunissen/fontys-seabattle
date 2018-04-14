package communication;

import models.Ship;

/**
 * een speler is gereed om te spelen
 */
public class PlaceShipPackage extends CommunicationPackage {
    public PlaceShipPackage(Ship ship) {
        super(CommunicationAction.PlaceShip, serialize(ship));
    }
    private static String serialize(Ship ship){
        return Ship.serialize(ship);
    }
    public static Ship unserialize(String data){
        try {
            return Ship.unserialize(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

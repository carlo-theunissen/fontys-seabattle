package communication;

import models.Ship;

/**
 * een speler is gereed om te spelen
 */
public class RemoveShipPackage extends CommunicationPackage {
    public RemoveShipPackage(Ship ship) {
        super(CommunicationAction.RemoveShip, serialize(ship));
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

package communication;

import models.Hit;
import models.HitType;

public class HitPackage extends CommunicationPackage {
    public HitPackage(Hit hit) {
        super(CommunicationAction.PlaceBoat, serialize(hit));
    }
    private static String serialize(Hit hit){
        return hit.getX() +":"+ hit.getY() + ":" + (hit.getHitType() == HitType.Collided ? "C" : "S");
    }
    public static Hit unserialize(String data){
        String[] split = data.split(":");
        return new Hit(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2].equals("C") ? HitType.Collided : HitType.Miss);
    }
}

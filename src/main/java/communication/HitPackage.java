package communication;

import models.Hit;

public class HitPackage extends CommunicationPackage {
    public HitPackage(Hit hit) {
        super(CommunicationAction.HitResponse, serialize(hit));
    }
    private static String serialize(Hit hit){
        return Hit.serialize(hit);
    }
    public static Hit unserialize(String data){
        return Hit.unserialize(data);
    }
}

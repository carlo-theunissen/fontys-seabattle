package communication;

import models.Hit;

public class FireReady extends CommunicationPackage {
    public FireReady() {
        super(CommunicationAction.FireReadyRespone, serialize());
    }
    private static String serialize(){
        return "";
    }
    public static String unserialize(String data){
        return "";
    }
}

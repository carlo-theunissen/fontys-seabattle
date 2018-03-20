package communication;

import game.GameExecutor;
import models.Fire;
import models.Hit;

public class SinglePlayerCommunication extends BaseCommunication implements ICommunication {
    private final GameExecutor otherPlayer;
    public SinglePlayerCommunication(GameExecutor opponent){
        otherPlayer = opponent;
    }
    public void sendPackage(CommunicationPackage communicationPackage) {
        if(communicationPackage.getAction() == CommunicationAction.HitResponse){
            Hit hit = HitPackage.unserialize(communicationPackage.getData());
            getLocalExecutor().FireResponse(hit);
        } else if(communicationPackage.getAction() == CommunicationAction.Fire){
            Fire fire = FirePackage.unserialize(communicationPackage.getData());
            getLocalExecutor().FireShot(fire);
        }
    }
}

package communication;

import game.GameExecutor;
import models.Fire;
import models.Hit;

public class SinglePlayerCommunication extends BaseCommunication implements ICommunication {
    private GameExecutor otherPlayer;

    public void setOtherPlayer(GameExecutor otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void sendPackage(CommunicationPackage communicationPackage) {
        if(communicationPackage.getAction() == CommunicationAction.HitResponse){
            Hit hit = HitPackage.unserialize(communicationPackage.getData());

            otherPlayer.FireResponse(hit);

        } else if(communicationPackage.getAction() == CommunicationAction.Fire){
            Fire fire = FirePackage.unserialize(communicationPackage.getData());
            getLocalExecutor().FireShot(fire);
        }
    }
}

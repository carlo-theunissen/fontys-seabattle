package communication;

import game.GameExecutor;
import models.Fire;
import models.Hit;

public class SinglePlayerCommunication extends BaseCommunication implements ICommunication {
    private GameExecutor otherPlayer;
    private LocalSinglePlayerManager playerServer;

    public void setOtherPlayer(GameExecutor otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void setPlayerServer(LocalSinglePlayerManager playerServer) {
        this.playerServer = playerServer;
    }

    public void sendPackage(CommunicationPackage communicationPackage) {
        switch (communicationPackage.getAction()){
            case PlaceBoat:
                break;
            case Fire:
                    Fire fire = FirePackage.unserialize(communicationPackage.getData());
                    otherPlayer.FireShot(fire);
                break;
            case Start:
                    getLocalExecutor().GameReady(StartPackage.unserialize( communicationPackage.getData()));
                break;
            case Ready:
                playerServer.registerPlayer(this, ReadyPackage.unserialize( communicationPackage.getData()));
                break;
            case RequestFireReady:
                playerServer.requestFireReady();
                break;
            case FireReadyRespone:
                otherPlayer.FireReady();
                break;
            case HitResponse:
                Hit hit = HitPackage.unserialize(communicationPackage.getData());
                otherPlayer.FireResponse(hit);
                break;
        }
    }
}

package communication;

import game.GameExecutor;
import game.GameManager;
import models.Fire;
import models.Hit;

public class SinglePlayerCommunication extends BaseCommunication implements ICommunication {
    private GameExecutor otherPlayer;
    private GameManager gameManager;

    public void setOtherPlayer(GameExecutor otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void sendPackage(CommunicationPackage communicationPackage) {
        switch (communicationPackage.getAction()){
            case PlaceShip:
                break;
            case RemoveShip:
                break;
            case Fire:
                    Fire fire = FirePackage.unserialize(communicationPackage.getData());
                    otherPlayer.FireShot(fire);
                break;
            case Start:
                    getLocalExecutor().GameStart(communicationPackage.getData());
                break;
            case Ready:
                gameManager.registerPlayer(this, ReadyPackage.unserialize( communicationPackage.getData()));
                break;
            case RequestFireReady:
                gameManager.requestFireReady();
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

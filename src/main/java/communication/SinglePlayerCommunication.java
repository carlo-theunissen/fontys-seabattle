package communication;

import gameLogic.GameManager;
import gameLogic.IGameExecutor;
import models.Fire;
import models.Hit;

public class SinglePlayerCommunication extends BaseCommunication implements ICommunication {
    private IGameExecutor otherPlayer;
    private GameManager gameManager;

    public void setOtherPlayer(IGameExecutor otherPlayer) {
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
                    otherPlayer.OpponentFiresOnOurGrid(fire);
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
                otherPlayer.StartFireState();
                break;
            case HitResponse:
                Hit hit = HitPackage.unserialize(communicationPackage.getData());
                otherPlayer.OpponentResponse(hit);
                break;
        }
    }
}

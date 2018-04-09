package websocketServer;

import communication.*;
import game.GameExecutor;
import game.GameManager;
import models.Fire;
import models.Hit;

public class ServerIntroLayer {
    MultiplayerSerialHelper helper;
    private GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    ServerIntroLayer(){
        helper = new MultiplayerSerialHelper();
    }
    public void postNewPackage(GameExecutor executor, String data){
        CommunicationPackage communcation = helper.unserializePackage(data);
    }

    private void handleNewPackage(CommunicationPackage communicationPackage, GameExecutor executor) throws Exception {
        switch (communicationPackage.getAction()){
            case PlaceBoat:
                executor.PlaceShip(BoatPackage.unserialize(communicationPackage.getData()));
                break;
            case Fire:
                Fire fire = FirePackage.unserialize(communicationPackage.getData());
                executor.FireShot(fire);
                break;
            case Start:
                executor.GameReady(StartPackage.unserialize( communicationPackage.getData()));
                break;
            case Ready:
                gameManager.registerPlayer(executor.getCommunication(), ReadyPackage.unserialize( communicationPackage.getData()));
                break;
            case RequestFireReady:
                gameManager.requestFireReady();
                break;
            case FireReadyRespone:
                break;
            case HitResponse:
                Hit hit = HitPackage.unserialize(communicationPackage.getData());
                executor.FireResponse(hit);
                break;
        }
    }
}

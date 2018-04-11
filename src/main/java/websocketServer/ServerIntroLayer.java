package websocketServer;

import communication.*;
import game.GameExecutor;
import game.GameManager;
import game.exceptions.GameException;
import models.Fire;
import models.Hit;

public class ServerIntroLayer {
    private MultiplayerSerialHelper helper;
    private GameExecutor opponnentExecutor;
    private GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    ServerIntroLayer(){
        helper = new MultiplayerSerialHelper();
    }
    public void postNewMessage(GameExecutor executor, String data){
        CommunicationPackage communcation = helper.unserializePackage(data);
        try {
            handleNewPackage(communcation, executor);
        } catch (GameException e) {
            handleGameException(e);
        }
    }

    public void setOpponnentExecutor(GameExecutor opponnentExecutor) {
        this.opponnentExecutor = opponnentExecutor;
    }

    private void handleNewPackage(CommunicationPackage communicationPackage, GameExecutor executor) throws GameException {
        switch (communicationPackage.getAction()){
            case PlaceBoat:
                executor.PlaceShip(BoatPackage.unserialize(communicationPackage.getData()));
                break;
            case Fire:
                //vuur dit op de tegenstander
                Fire fire = FirePackage.unserialize(communicationPackage.getData());
                opponnentExecutor.FireShot(fire);
                executor.FireOpponent(fire);
                break;
            case Start:
                executor.GameReady(StartPackage.unserialize( communicationPackage.getData()));
                break;
            case Ready:
                executor.PlayerStart(ReadyPackage.unserialize( communicationPackage.getData()));
                gameManager.registerPlayer(executor.getCommunication(), ReadyPackage.unserialize( communicationPackage.getData()));
                break;
            case RequestFireReady:
                executor.RequestFireReady();
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

    /**
     * We should handle the exception here and send an exception package to the client
     * @param exception
     */
    private void handleGameException(GameException exception){
        System.out.println(exception.getMessage());
    }


}

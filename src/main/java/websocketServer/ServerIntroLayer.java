package websocketServer;

import communication.*;
import game.GameExecutor;
import game.GameManager;
import game.exceptions.GameException;
import models.Fire;
import models.Hit;

public class ServerIntroLayer {
    private MultiplayerSerialHelper helper;
    private GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    ServerIntroLayer(){
        helper = new MultiplayerSerialHelper();
    }


    public void postNewMessage(GameExecutor executor, GameExecutor opponnentExecutor, String data){
        CommunicationPackage communcation = helper.unserializePackage(data);
        try {
            handleNewPackage(communcation, executor, opponnentExecutor);
        } catch (GameException e) {
            handleGameException(e);
        }
    }


    private void handleNewPackage(CommunicationPackage communicationPackage, GameExecutor executor, GameExecutor opponnentExecutor) throws GameException {
        switch (communicationPackage.getAction()){
            case PlaceShip:
                executor.PlaceShip(PlaceShipPackage.unserialize(communicationPackage.getData()));
                break;
            case Fire:
                //vuur dit op de tegenstander
                Fire fire = FirePackage.unserialize(communicationPackage.getData());
                opponnentExecutor.OpponentFiresOnOurGrid(fire);
                executor.FireOnGridOpponent(fire);
                break;
            case Start:
                executor.GameStart(StartPackage.unserialize( communicationPackage.getData()));
                break;
            case Ready:
                executor.PlayerReady(ReadyPackage.unserialize( communicationPackage.getData()));
                gameManager.registerPlayer(executor.getCommunication(), ReadyPackage.unserialize( communicationPackage.getData()));
                break;
            case RequestFireReady:
                executor.RequestFireState();
                gameManager.requestFireReady();
                break;
            case FireReadyRespone:
                break;
            case HitResponse:
                Hit hit = HitPackage.unserialize(communicationPackage.getData());
                executor.OpponentResponse(hit);
                break;
        }
    }

    /**
     * We should handle the exception here and send an exception package to the client
     * @param exception
     */
    private void handleGameException(GameException exception){
        exception.printStackTrace(System.err);
    }


    public GameManager getGameManager() {
        return gameManager;
    }
}

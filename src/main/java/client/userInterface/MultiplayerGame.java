package client.userInterface;

import communication.MultiplayerLocalToServerCommunication;
import communication.SinglePlayerCommunication;
import game.GameExecutor;
import game.IUIExecutor;
import game.exceptions.PlayerStartException;
import models.Orientation;
import models.Ship;
import models.ShipType;
import models.ShotType;

import javax.websocket.DeploymentException;
import java.io.IOException;

public class MultiplayerGame extends BaseGame implements ISeaBattleGame {

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        localPlayer.setGUIExecutor( GUIExecutor );
    }

    public MultiplayerGame(){

        MultiplayerLocalToServerCommunication communcationFromPlayerToOpponent = null;
        try {
            communcationFromPlayerToOpponent = new MultiplayerLocalToServerCommunication();
        } catch (IOException | DeploymentException e) {
            e.printStackTrace();
        }

        localPlayer = new GameExecutor(communcationFromPlayerToOpponent);
        localPlayer.setGridSize(10,10);
    }

    @Override
    public GameExecutor getPlayer(int playerNr) {
    return localPlayer;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        try {
            localPlayer.PlayerStart(name);
        } catch (PlayerStartException e) {
            e.printStackTrace();
        }
        return 2;
    }
}

package client.userInterface;

import communication.MultiplayerLocalToServerCommunication;
import gameLogic.GameExecutor;
import gameLogic.IGameExecutor;
import gameLogic.IUIExecutor;
import gameLogic.exceptions.PlayerStartException;

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
    public IGameExecutor getPlayer(int playerNr) {
        return localPlayer;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        try {
            localPlayer.PlayerReady(name);
        } catch (PlayerStartException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

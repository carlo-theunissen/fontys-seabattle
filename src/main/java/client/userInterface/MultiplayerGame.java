package client.userInterface;

import communication.SinglePlayerCommunication;
import game.GameExecutor;
import game.IUIExecutor;
import models.Orientation;
import models.Ship;
import models.ShipType;
import models.ShotType;

public class MultiplayerGame extends BaseGame implements ISeaBattleGame {

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        localPlayer.setGUIExecutor( GUIExecutor );
    }

    public MultiplayerGame(){
        SinglePlayerCommunication communcationFromPlayerToOpponent = new SinglePlayerCommunication();
        localPlayer = new GameExecutor(communcationFromPlayerToOpponent);

        SinglePlayerCommunication communicationFromOpponentToPlayer = new SinglePlayerCommunication();
        communicationFromOpponentToPlayer.setOtherPlayer(localPlayer);

        localPlayer.setGridSize(10,10);
    }

    @Override
    public GameExecutor getPlayer(int playerNr) {
    return localPlayer;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        localPlayer.PlayerStart(name);

        return 2;
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        return false;
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        return null;
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        return null;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        return false;
    }
}

package client.userInterface;

import communication.SinglePlayerCommunication;
import game.GameExecutor;
import game.IUIExecutor;
import models.Orientation;
import models.Ship;
import models.ShipType;
import models.ShotType;

public class MultiplayerGame extends BaseGame implements ISeaBattleGame {

    public MultiplayerGame(){
        SinglePlayerCommunication communcationFromPlayerToOpponent = new SinglePlayerCommunication();
        localPlayer = new GameExecutor(communcationFromPlayerToOpponent);

        SinglePlayerCommunication communicationFromOpponentToPlayer = new SinglePlayerCommunication();
        communicationFromOpponentToPlayer.setOtherPlayer(localPlayer);

        opponentPlayer = new GameExecutor(communicationFromOpponentToPlayer);

        communcationFromPlayerToOpponent.setOtherPlayer(opponentPlayer);
        localPlayer.setGridSize(10,10);
        opponentPlayer.setGridSize(10,10);
    }

    public GameExecutor getPlayer(int playerNr){
        GameExecutor player;
        if (playerNr == 0) {
            player = localPlayer;
        } else if(playerNr == 1){
            player = opponentPlayer;
        } else {
            throw new IllegalArgumentException("Playernumber cannot be more then 1 or be negative");
        }
        return player;
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {

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

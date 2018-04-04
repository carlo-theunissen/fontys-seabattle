package client.userInterface;

import communication.SinglePlayerCommunication;
import game.GameExecutor;
import game.IUIExecutor;
import helpers.CollideHelper;
import models.*;

import java.util.Collection;

public class SinglePlayerGame extends BaseGame implements ISeaBattleGame {



    private GameExecutor opponentPlayer;

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        opponentPlayer.setGUIExecutor( new EmptySeaBattleGUI() );
        localPlayer.setGUIExecutor( GUIExecutor );
    }

    public SinglePlayerGame(){
        SinglePlayerCommunication communcationFromPlayerToAI = new SinglePlayerCommunication();
        localPlayer = new GameExecutor(communcationFromPlayerToAI);

        SinglePlayerCommunication communicationFromAIToPlayer = new SinglePlayerCommunication();
        communicationFromAIToPlayer.setOtherPlayer(localPlayer);

        opponentPlayer = new GameExecutor(communicationFromAIToPlayer);

        communcationFromPlayerToAI.setOtherPlayer(opponentPlayer);


        localPlayer.setGridSize(10,10);
        opponentPlayer.setGridSize(10,10);
    }

    @Override
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

    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        PlaceShipsAI();
        opponentPlayer.PlayerStart("AI");
        localPlayer.PlayerStart(name);

        return 0;
    }

    public void PlaceShipsAI(){
        placeShip(1, ShipType.AIRCRAFTCARRIER, 0,0, true);
        placeShip(1, ShipType.BATTLESHIP, 0,1, true);
        placeShip(1, ShipType.CRUISER, 0,2, true);
        placeShip(1, ShipType.MINESWEEPER, 0,3, true);
        placeShip(1, ShipType.SUBMARINE, 0,4, true);
    }

    public boolean notifyWhenReady(int playerNr) {
        GameExecutor player = getPlayer(playerNr);
        Collection<Ship> ships = player.GetLocalGrid().getShips();
        if (ships.size() == 5){
            return true;
        } else {
            return false;
        }
    }

    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        System.out.println("hopelijk niet dubbel");
        GameExecutor player = getPlayer(playerNr);
        player.FireOpponent(new Fire(posX, posY));
        return ShotType.MISSED;
    }

    /**
     * Let the opponent fire a shot at the player's square.
     * This method is used in the single-player mode.
     * A shot is fired by the opponent using some AI strategy.
     * The result of the shot will be one of the following:
     * MISSED  - No ship was hit
     * HIT     - A ship was hit
     * SUNK    - A ship was sunk
     * ALLSUNK - All ships are sunk
     * @param playerNr identification of the player for which the opponent
     *                 will fire a shot
     * @return result of the shot
     */
    public ShotType fireShotOpponent(int playerNr) {
        if(playerNr != 0){
            return ShotType.MISSED;
        }
        opponentPlayer.FireOpponent(new Fire((int)(Math.random()* 10), (int)(Math.random()* 10)));
        return ShotType.MISSED;
    }
// -TODO Alex implemnteren
    public boolean startNewGame(int playerNr) {
        return true;
    }

}

package userInterface;

import communication.SinglePlayerCommunication;
import game.GameExecutor;
import game.IUIExecutor;
import helpers.CollideHelper;
import models.*;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SinglePlayerGame implements ISeaBattleGame {

    private GameExecutor localPlayer;
    private GameExecutor aiPlayer;


    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        aiPlayer.setGUIExecutor( new EmptySeaBattleGUI() );
        localPlayer.setGUIExecutor( GUIExecutor );
    }

    public SinglePlayerGame(){
        SinglePlayerCommunication communcationFromPlayerToAI = new SinglePlayerCommunication();
        localPlayer = new GameExecutor(communcationFromPlayerToAI);

        SinglePlayerCommunication communicationFromAIToPlayer = new SinglePlayerCommunication();
        communicationFromAIToPlayer.setOtherPlayer(localPlayer);

        aiPlayer = new GameExecutor(communicationFromAIToPlayer);

        communcationFromPlayerToAI.setOtherPlayer(aiPlayer);
        localPlayer.setGridSize(10,10);
        aiPlayer.setGridSize(10,10);
    }

    public GameExecutor getPlayer(int playerNr){
        GameExecutor player;
        if (playerNr == 0) {
            player = localPlayer;
        } else if(playerNr == 1){
            player = aiPlayer;
        } else {
            throw new IllegalArgumentException("Playernumber cannot be more then 1 or be negative");
        }
        return player;
    }

    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        PlaceShipsAI();
        return 0;
    }

    public void PlaceShipsAI(){
        placeShip(1, ShipType.AIRCRAFTCARRIER, 0,0, true);
        placeShip(1, ShipType.BATTLESHIP, 0,1, true);
        placeShip(1, ShipType.CRUISER, 0,2, true);
        placeShip(1, ShipType.MINESWEEPER, 0,3, true);
        placeShip(1, ShipType.SUBMARINE, 0,4, true);
    }

    public boolean placeShipsAutomatically(int playerNr) {
        removeAllShips(playerNr);
        placeShip(playerNr, ShipType.AIRCRAFTCARRIER, 0,0, true);
        placeShip(playerNr, ShipType.BATTLESHIP, 0,1, true);
        placeShip(playerNr, ShipType.CRUISER, 0,2, true);
        placeShip(playerNr, ShipType.MINESWEEPER, 0,3, true);
        placeShip(playerNr, ShipType.SUBMARINE, 0,4, true);

        return true;
    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        GameExecutor player = getPlayer(playerNr);
        Ship ship = new Ship(shipType);
        ship.setX(bowX);
        ship.setY(bowY);
        if (horizontal) {
            ship.setOrientation(Orientation.Horizontal);
        } else {
            ship.setOrientation(Orientation.Vertical);
        }
        player.PlaceBoat(ship);

        return true;
    }

    public boolean removeShip(int playerNr, int posX, int posY) {
        GameExecutor player = getPlayer(playerNr);
        return removeShip(playerNr, (new CollideHelper()).getShip(posX, posY, player.GetLocalGrid()));
    }

    private boolean removeShip(int playerNr, Ship ship) {
        GameExecutor player = getPlayer(playerNr);
        if(ship != null){
            player.RemoveShip(ship);
        }

        return ship != null;
    }

    public boolean removeAllShips(int playerNr) {
        GameExecutor player = getPlayer(playerNr);

        Ship[] array = new Ship[player.GetLocalGrid().getShips().size()];
        int i = 0;
        for (Ship ship:  player.GetLocalGrid().getShips()) {
            array[i++] = ship;
        }

        for(Ship ship: array){
            removeShip(playerNr,ship);
        }

        return true;
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
        aiPlayer.FireOpponent(new Fire((int)(Math.random()* 10), (int)(Math.random()* 10)));
        return ShotType.MISSED;
    }

    public boolean startNewGame(int playerNr) {
        return false;
    }
}

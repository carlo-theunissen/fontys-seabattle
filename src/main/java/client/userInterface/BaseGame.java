package client.userInterface;

import game.GameExecutor;
import game.IUIExecutor;
import game.exceptions.BoatInvalidException;
import game.exceptions.PlayerStartException;
import helpers.CollideHelper;
import models.Orientation;
import models.Ship;
import models.ShipType;
import models.ShotType;

public abstract class BaseGame implements ISeaBattleGame{

    protected GameExecutor localPlayer;
    protected ISeaBattleEnhancedGUI enhancedGUI;

    public void setEnhancedGUI(ISeaBattleEnhancedGUI enhancedGUI) {
        this.enhancedGUI = enhancedGUI;
    }

    public abstract void setGUIExecutor(IUIExecutor GUIExecutor);

    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        return 0;
    }

    public abstract GameExecutor getPlayer(int playerNr);

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        removeAllShips(playerNr);
        placeShip(playerNr, ShipType.AIRCRAFTCARRIER, 0,0, true);
        placeShip(playerNr, ShipType.BATTLESHIP, 0,1, true);
        placeShip(playerNr, ShipType.CRUISER, 0,2, true);
        placeShip(playerNr, ShipType.MINESWEEPER, 0,3, true);
        placeShip(playerNr, ShipType.SUBMARINE, 0,4, true);

        return true;
    }

    @Override
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
        try {
            player.PlaceShip(ship);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return true;
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        GameExecutor player = getPlayer(playerNr);
        return removeShip(playerNr, (new CollideHelper()).getShip(posX, posY, player.GetLocalGrid()));
    }

    private boolean removeShip(int playerNr, Ship ship) {
        GameExecutor player = getPlayer(playerNr);
        if(ship != null){
            try {
                player.RemoveShip(ship);
            } catch (PlayerStartException e) {
                e.printStackTrace();
            } catch (BoatInvalidException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
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

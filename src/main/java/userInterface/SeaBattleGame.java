package userInterface;

import game.GameExecutor;
import game.ShipGrid;
import models.Orientation;
import models.Ship;
import models.ShipType;
import models.ShotType;

import java.util.Collection;

public class SeaBattleGame implements ISeaBattleGame {

    private GameExecutor player0 = new GameExecutor();
    private GameExecutor player1 = new GameExecutor();

    public GameExecutor getPlayer(int playerNr){
        GameExecutor player;
        if (playerNr == 0) {
            player = player0;
        } else if(playerNr == 1){
            player = player1;
        } else {
            throw new IllegalArgumentException("Playernumber cannot be more then 1 or be negative");
        }
        return player;
    }

    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        return 0;
    }

    public boolean placeShipsAutomatically(int playerNr) {
        return false;
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

        Collection<Ship> ships = player.GetLocalGrid().getShips();
            for (Ship ship : ships) {
                for (int i = 0; i < ship.getLength(); i++) {
                    if (ship.getOrientation() == Orientation.Horizontal) {
                        if ((ship.getX() + i) == posX && ship.getY() == posY) {
                            player.GetLocalGrid().removeShip(ship);
                        }
                    } else {
                        if (ship.getX() == posX && (ship.getY() + 1 == posY)) {
                            player.GetLocalGrid().removeShip(ship);
                        }
                    }
                }
            }

        return true;
    }

    public boolean removeAllShips(int playerNr) {
        GameExecutor player = getPlayer(playerNr);
        player.GetLocalGrid().removeAllShips();

        return true;
    }

    public boolean notifyWhenReady(int playerNr) {
        return false;
    }

    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        return null;
    }

    public ShotType fireShotOpponent(int playerNr) {
        return null;
    }

    public boolean startNewGame(int playerNr) {
        return false;
    }
}

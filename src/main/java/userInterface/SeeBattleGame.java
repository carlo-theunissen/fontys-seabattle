package userInterface;

import models.ShipType;
import models.ShotType;

public class SeeBattleGame implements ISeaBattleGame {
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        return 0;
    }

    public boolean placeShipsAutomatically(int playerNr) {
        return false;
    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        return false;
    }

    public boolean removeShip(int playerNr, int posX, int posY) {
        return false;
    }

    public boolean removeAllShips(int playerNr) {
        return false;
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

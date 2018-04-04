package client.userInterface;

import game.IUIExecutor;
import models.Hit;
import models.Ship;
import models.ShipType;

public class EmptySeaBattleGUI implements IUIExecutor {
    @Override
    public void placeTempShipLocal(ShipType shipType, int bowX, int bowY, boolean horizontal) {

    }

    @Override
    public void placeShipLocal(Ship ship) {

    }

    @Override
    public void removeShipLocal(Ship ship) {

    }

    @Override
    public void fireShotLocal(Hit hit) {

    }

    @Override
    public void fireShotOpponent(Hit hit) {

    }

    @Override
    public void fireTempShotOpponent(int posX, int posY) {

    }

    @Override
    public void removeTempShotOpponent() {

    }

    @Override
    public void removeTempShipLocal(int posX, int posY) {

    }

    @Override
    public void gameReady(String opponentName) {

    }
}

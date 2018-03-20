package userInterface;

import game.IUIExecutor;
import models.Hit;
import models.Ship;
import models.ShipType;
import models.SquareState;

public class SeaBattleGUI implements IUIExecutor {

    private ISeaBattleGUI battleGUI;
    public SeaBattleGUI(ISeaBattleGUI gui){
        battleGUI = gui;
    }

    @Override
    public void placeTempShipLocal(ShipType shipType, int bowX, int bowY, boolean horizontal) {

    }

    @Override
    public void placeShipLocal(Ship ship) {
        ship.getLength();
        ship.getOrientation();
        for (int i = 0; i < ship.getLength(); i++)
        battleGUI.showSquarePlayer(0, ship.getX(), ship.getY(), SquareState.SHIP);
    }

    @Override
    public void fireShotLocal(Hit hit) {
        battleGUI.opponentFiresShot(hit);
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
}

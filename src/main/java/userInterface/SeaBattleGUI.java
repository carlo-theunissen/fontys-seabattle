package userInterface;

import game.IUIExecutor;
import models.*;

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

        for (int i = 0; i < ship.getLength(); i++) {
            if(ship.getOrientation() == Orientation.Horizontal) {
                battleGUI.showSquarePlayer(0, ship.getX() + i, ship.getY(), SquareState.SHIP);
            } else {
                battleGUI.showSquarePlayer(0, ship.getX(), ship.getY() + i, SquareState.SHIP);
            }
        }
    }

    @Override
    public void fireShotLocal(Hit hit) {
        //battleGUI.opponentFiresShot(1);
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

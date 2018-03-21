package userInterface;

import game.IUIExecutor;
import models.*;

import static models.HitType.Collided;
import static models.HitType.Miss;

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
        for (int i = 0; i < ship.getLength(); i++) {
            if(ship.getOrientation() == Orientation.Horizontal) {
                battleGUI.showSquarePlayer(0, ship.getX() + i, ship.getY(), SquareState.SHIP);
            } else {
                battleGUI.showSquarePlayer(0, ship.getX(), ship.getY() + i, SquareState.SHIP);
            }
        }
    }

    @Override
    public void removeShipLocal(Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) {
            if(ship.getOrientation() == Orientation.Horizontal) {
                battleGUI.showSquarePlayer(0, ship.getX() + i, ship.getY(), SquareState.WATER);
            } else {
                battleGUI.showSquarePlayer(0, ship.getX(), ship.getY() + i, SquareState.WATER);
            }
        }
    }

    @Override
    public void fireShotLocal(Hit hit) {
        HitType hitType = hit.getHitType();
        if (hitType == Miss) {
            battleGUI.showSquarePlayer(1, hit.getX(), hit.getY(), SquareState.SHOTMISSED);
        }
        else if (hitType == Collided) {
            battleGUI.showSquarePlayer(1, hit.getX(), hit.getY(), SquareState.SHOTHIT);
        }
    }

    @Override
    public void fireShotOpponent(Hit hit) {
        HitType hitType = hit.getHitType();
        if (hitType == Miss) {
            battleGUI.showSquareOpponent(0, hit.getX(), hit.getY(), SquareState.SHOTMISSED);
        }
        else if (hitType == Collided) {
            battleGUI.showSquareOpponent(0, hit.getX(), hit.getY(), SquareState.SHOTHIT);
        }
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

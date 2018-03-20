package helpers;

import game.ShipGrid;
import models.Orientation;
import models.Ship;

import java.util.Collection;

public class CollideHelper {
    public Ship getShip(int x, int y, ShipGrid grid){
        for (Ship ship : grid.getShips()) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getOrientation() == Orientation.Horizontal) {
                    if ((ship.getX() + i) == x && ship.getY() == y) {
                        return ship;
                    }
                } else {
                    if (ship.getX() == x && (ship.getY() + 1 == y)) {
                        return ship;
                    }
                }
            }
        }
        return null;
    }
}
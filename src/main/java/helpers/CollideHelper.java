package helpers;

import gameLogic.ShipGrid;
import models.Orientation;
import models.Ship;

public class CollideHelper {
    public Ship getShip(int x, int y, ShipGrid grid){
        for (Ship ship : grid.getShips()) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getOrientation() == Orientation.Horizontal) {
                    if ((ship.getX() + i) == x && ship.getY() == y) {
                        return ship;
                    }
                } else {
                    if (ship.getX() == x && (ship.getY() + i == y)) {
                        return ship;
                    }
                }
            }
        }
        return null;
    }
}

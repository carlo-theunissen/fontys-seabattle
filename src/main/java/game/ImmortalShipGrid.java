package game;

import models.Hit;
import models.Ship;

/**
 * This class prevents a grid for being edited.
 */
public class ImmortalShipGrid extends ShipGrid {

    public ImmortalShipGrid(ShipGrid grid){
        super(grid);
        ships = grid.ships;
    }

    @Override
    public void setShip(Ship ship) {
        throw new IllegalStateException();
    }

    @Override
    public void setWidth(int value) {
        throw new IllegalStateException();
    }

    @Override
    public void setHeight(int value) {
        throw new IllegalStateException();
    }

    @Override
    public void AddHit(Hit hit) {
        throw new IllegalStateException();
    }

    @Override
    public boolean removeShip(Ship ship) {
        throw new IllegalStateException(); //sorry
    }
}
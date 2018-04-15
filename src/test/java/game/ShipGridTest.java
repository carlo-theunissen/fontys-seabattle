package game;

import models.Ship;
import models.ShipType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ShipGridTest {

    @DataPoints
    public ShipGrid GetShipGrid(){
        return new ShipGrid();
    }

    @Test
    public void ConstructorTest(){
        Grid grid = new Grid();
        ShipGrid shipGrid = new ShipGrid(grid);
        Assert.assertNotNull(shipGrid);
    }
    @Test
    public void getShips() {
        ShipGrid shipGrid = GetShipGrid();
        Ship ship = new Ship(ShipType.BATTLESHIP);
        Ship ship2 = new Ship(ShipType.CRUISER);
        shipGrid.setShip(ship);
        shipGrid.setShip(ship2);
        Collection<Ship> ships = shipGrid.getShips();
        Assert.assertTrue(ships.contains(ship));
        Assert.assertTrue(ships.contains(ship2));
    }

    @Test
    public void setShip() {
        ShipGrid shipGrid = GetShipGrid();
        Ship ship = new Ship(ShipType.BATTLESHIP);
        shipGrid.setShip(ship);
        Collection<Ship> ships = shipGrid.getShips();
        Assert.assertTrue(ships.contains(ship));
    }

    @Test
    public void removeShip() {
        ShipGrid shipGrid = GetShipGrid();
        Ship ship = new Ship(ShipType.BATTLESHIP);
        shipGrid.setShip(ship);
        shipGrid.removeShip(ship);
        Collection<Ship> ships = shipGrid.getShips();
        Assert.assertFalse(ships.contains(ship));
    }
}
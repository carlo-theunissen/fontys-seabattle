package models;


import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

public class ShipTest {

    @DataPoints
    public ShipType[] GetShipTypes(){
        return new ShipType[] {
                ShipType.AIRCRAFTCARRIER,
                ShipType.MINESWEEPER,
                ShipType.BATTLESHIP,
                ShipType.CRUISER,
                ShipType.SUBMARINE
        };
    }

    @Theory
    public void ValidConstructorTest(ShipType shipType){
        Ship ship = new Ship(shipType);
        Assert.assertNotNull(ship);
    }

    @Test(expected = IllegalArgumentException.class)
    public void InValidConstructorTest(){
        Ship ship = new Ship(null);
    }

    @Test
    public void SetOrientationTest(){
        Ship ship = new Ship(ShipType.AIRCRAFTCARRIER);
        ship.setOrientation(Orientation.Horizontal);
        Assert.assertEquals(Orientation.Horizontal, ship.getOrientation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidOrientationTest(){
        Ship ship = new Ship(ShipType.AIRCRAFTCARRIER);
        ship.setOrientation(null);
    }

    @Theory
    public void GetSizeOfShip(ShipType shipType){
        Ship ship = new Ship(shipType);

        switch (shipType){
            case BATTLESHIP:
                Assert.assertEquals(4, ship.getLength());
                break;
            case AIRCRAFTCARRIER:
                Assert.assertEquals(5, ship.getLength());
                break;
            case CRUISER:
                Assert.assertEquals(3, ship.getLength());
                break;
            case MINESWEEPER:
        }

    }
}

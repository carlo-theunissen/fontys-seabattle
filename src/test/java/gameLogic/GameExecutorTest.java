package gameLogic;

import communication.CommunicationAction;
import communication.CommunicationPackage;
import communication.ICommunication;
import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameExecutorTest {

    private CommunicationMock communication;
    private UIMock uiMock;

    @Before
    public void install(){
        communication = new CommunicationMock();
        uiMock = new UIMock();
    }

    private IGameExecutor getExecutor(){

        GameExecutor executor = new GameExecutor(communication);
        executor.setGUIExecutor(uiMock);
        executor.setGridSize(10,10);
        return executor;
    }

    @Test
    public void getCommunicationTest(){
        Assert.assertSame(communication, getExecutor().getCommunication());
    }

    @Test
    public void DefaultLocalGridTest(){
        ShipGrid grid = getExecutor().GetLocalGrid();
        Assert.assertNotNull(grid);

        Assert.assertEquals(0, grid.ships.size());
        Assert.assertEquals(10, grid.getHeight());
        Assert.assertEquals(10, grid.getWidth());
    }


    @Test
    public void setGridSize(){

        IGameExecutor executor = getExecutor();

        executor.setGridSize(20,20);
        ShipGrid grid = executor.GetLocalGrid();
        Assert.assertEquals(20, grid.getHeight());
        Assert.assertEquals(20, grid.getWidth());
    }

    @Test
    public void RemoveShip() throws Exception {

        IGameExecutor executor = getExecutor();
        Ship ship = new Ship(ShipType.CRUISER);
        ship.setOrientation(Orientation.Horizontal);
        ship.setX(0);
        ship.setY(0);

        executor.PlayerReady("test");
        executor.PlaceShip(ship);

        Assert.assertEquals(1, executor.GetLocalGrid().ships.size());
        executor.RemoveShip(ship);
        Assert.assertEquals(0, executor.GetLocalGrid().ships.size());

        Assert.assertEquals(CommunicationAction.RemoveShip, communication.lastPackage.getAction());
        Ship communicatedShip = Ship.unserialize( communication.lastPackage.getData());
        Assert.assertEquals(ship, communicatedShip);

        Assert.assertEquals("removeShipLocal", uiMock.lastMethodCalled);
        Assert.assertEquals(ship, (Ship) uiMock.lastParameter);
    }


    @Test
    public void PlaceShip() throws Exception {
        IGameExecutor executor = getExecutor();
        Ship ship = new Ship(ShipType.CRUISER);
        ship.setOrientation(Orientation.Horizontal);
        ship.setX(0);
        ship.setY(0);

        Assert.assertEquals(0, executor.GetLocalGrid().ships.size());

        executor.PlayerReady("test");
        executor.PlaceShip(ship);

        Assert.assertEquals(1, executor.GetLocalGrid().ships.size());

        Assert.assertEquals(CommunicationAction.PlaceShip, communication.lastPackage.getAction());
        Ship communicatedShip = Ship.unserialize( communication.lastPackage.getData());
        Assert.assertEquals(ship, communicatedShip);

        Assert.assertEquals("placeShipLocal", uiMock.lastMethodCalled);
        Assert.assertEquals(ship, (Ship) uiMock.lastParameter);
    }
    private class CommunicationMock implements ICommunication{

        public CommunicationPackage lastPackage;
        @Override
        public void setLocalExecutor(IGameExecutor localExecutor) {

        }

        @Override
        public void sendPackage(CommunicationPackage communicationPackage) {
            lastPackage = communicationPackage;
        }
    }

    private class UIMock implements IUIExecutor{

        public String lastMethodCalled;
        public Object lastParameter;

        @Override
        public void placeShipLocal(Ship ship) {
            lastMethodCalled = "placeShipLocal";
            lastParameter = ship;
        }

        @Override
        public void removeShipLocal(Ship ship) {
            lastMethodCalled = "removeShipLocal";
            lastParameter = ship;
        }

        @Override
        public void fireShotLocal(Hit hit) {

        }

        @Override
        public void fireShotOpponent(Hit hit) {

        }


        @Override
        public void gameReady(String opponentName) {

        }

        @Override
        public void fireReady() {

        }

        @Override
        public void gameEnded(String winner) {

        }
    }
}
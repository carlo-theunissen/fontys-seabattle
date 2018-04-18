package gameLogic;

import communication.CommunicationPackage;
import communication.ICommunication;
import communication.PackageCommunication;
import gameLogic.exceptions.BoatInvalidException;
import gameLogic.exceptions.FireInvalidException;
import gameLogic.exceptions.PlayerNotTurnException;
import gameLogic.exceptions.PlayerStartException;
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
    public void RemoveShip() throws PlayerStartException, BoatInvalidException {

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
    }


    @Test
    public void PlaceShip() throws BoatInvalidException, PlayerStartException{
        IGameExecutor executor = getExecutor();
        Ship ship = new Ship(ShipType.CRUISER);
        ship.setOrientation(Orientation.Horizontal);
        ship.setX(0);
        ship.setY(0);

        Assert.assertEquals(0, executor.GetLocalGrid().ships.size());

        executor.PlayerReady("test");
        executor.PlaceShip(ship);

        Assert.assertEquals(1, executor.GetLocalGrid().ships.size());

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
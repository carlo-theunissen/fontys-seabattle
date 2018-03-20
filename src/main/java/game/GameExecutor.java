package game;

import communication.ICommunication;
import models.Fire;
import models.Hit;
import models.Ship;

/**
 * GameExecutor keeps track of the state of the game, all actions done in here are final
 */
public class GameExecutor {

    private ICommunication communication;
    public GameExecutor(ICommunication communication){
        this.communication = communication;
        this.communication.setLocalExecutor(this);

        shipGrid = new ShipGrid();
    }
	private ShipGrid shipGrid;
	private Grid grid;


	public ShipGrid GetLocalGrid() {
	    //prevent the grid being edited outside
		return new ImmortalShipGrid(grid);
	}

	/**
	 *
	 * @param ship
	 */
	public void PlaceBoat(Ship ship) {
		shipGrid.setShip(ship);
	}

    /**
     * Fire a shot on the local grid.
     * We should response with a hit
     * @param fire
     */
	public void FireShot(Fire fire) {
		// TODO - implement GameExcutor.FireShot
		throw new UnsupportedOperationException();
	}

    /**
     * Fire on the grid of the opponent
     * @param fire
     */
	public void FireOpponent(Fire fire){

    }

    /**
     * The response of the opponent
     * @param hit
     */
	public void FireResponse(Hit hit){

    }

}
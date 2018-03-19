package game;

import models.Hit;
import models.Ship;

/**
 * GameExecutor keeps track of the state of the game, all actions done in here are final
 */
public class GameExecutor {

	private ShipGrid shipGrid = new ShipGrid();
	private Grid grid;


	public Grid GetLocalGrid() {
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

	public void FireShot(Hit shot) {
		// TODO - implement GameExcutor.FireShot
		throw new UnsupportedOperationException();
	}

}
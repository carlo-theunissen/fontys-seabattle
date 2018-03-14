package game;

import java.util.Collection;

public class ShipGrid extends Grid {

	private Collection<Ship> ships;

	public Collection<Ship> getShips() {
		return ships;
	}

	/**
	 *
	 * @param ship
	 */
	public void setShip(Ship ship) {
		ships.add(ship);
	}

}
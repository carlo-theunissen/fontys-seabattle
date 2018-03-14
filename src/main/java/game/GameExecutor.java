package game;

public class GameExecutor {

	private ShipGrid shipGrid = new ShipGrid();
	private Grid grid;

	public Grid GetLocalGrid() {
		return grid;
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
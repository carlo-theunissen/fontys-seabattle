package gameLogic;

import models.Ship;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ShipGrid extends Grid {

	protected Collection<Ship> ships;

	public ShipGrid(){
		//we're using HashSet because the ships should be unique. It is not possible to add multiple of the same ship to the list
		ships = new HashSet<Ship>();
	}
	public ShipGrid(Grid grid){
		super(grid);
		ships = new HashSet<Ship>();
	}
	public Collection<Ship> getShips() {
		//don't let "ships" escape otherwise we'll lose responsibility
		return Collections.unmodifiableCollection(ships);
	}

	/**
	 *
	 * @param ship
	 */
	public void setShip(Ship ship) {
		ships.add(ship);
	}

	public boolean removeShip(Ship ship){
		return ships.remove(ship);
	}
}
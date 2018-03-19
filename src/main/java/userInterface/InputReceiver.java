package userInterface;

import game.GameExecutor;
import game.Grid;
import game.ShipGrid;
import game.UIExecutor;
import models.Player;
import models.Ship;

import java.util.Collection;

/**
 * The InputReceiver decides what to do with user input
 */
public class InputReceiver {

	private GameExecutor gameExecutor = new GameExecutor();
	private ShipGrid shipGrid = new ShipGrid();
	private UIExecutor uiExecutor = new UIExecutor();
	/**
	 * 
	 * @param userInput
	 */
	public void setInput(int userInput) {
		// TODO - implement InputReceiver.setInput
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param GameExecutor
	 */
	public void setGameGameExecutor(int GameExecutor) {
		// TODO - implement InputReceiver.setGameGameExecutor
		throw new UnsupportedOperationException();
	}

	/**
	 * finish the placeTempShip
	 */
	public void LeftMouseButtonDown(){
		Grid grid = gameExecutor.GetLocalGrid();
		Collection<Ship> ships = shipGrid.getShips();
		CalculateBoatStatus();
		Player player = grid.getPlayer();
		//uiExecutor.placeTempShip(player.getId());
	}

	public void CalculateBoatStatus(){

	}

}
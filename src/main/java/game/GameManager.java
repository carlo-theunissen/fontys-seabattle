package game;

import java.util.Collection;

public class GameManager {

	private GameExecutor gameExecutor = new GameExecutor();
	private ShipGrid shipGrid = new ShipGrid();
	private UIExecutor uiExecutor = new UIExecutor();
	/**
	 * 
	 * @param userInput
	 */
	public void setInput(int userInput) {
		// TODO - implement GameManager.setInput
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param GameExecutor
	 */
	public void setGameGameExecutor(int GameExecutor) {
		// TODO - implement GameManager.setGameGameExecutor
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
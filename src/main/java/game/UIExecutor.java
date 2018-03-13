package game;

public class UIExecutor implements ISeaBattleGame {

	public void placeTempShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
		// TODO - implement UIExecutor.PlaceTempBoat
		throw new UnsupportedOperationException();
	}

	public void fireTempShot() {
		// TODO - implement UIExecutor.PlaceTempShot
		throw new UnsupportedOperationException();
	}

	public void removeTempShip(int playerNr, int posX, int posY) {
		// TODO - implement UIExecutor.RemoveTempBoat
		throw new UnsupportedOperationException();
	}

	public void removeTempShot() {
		// TODO - implement UIExecutor.RemoveTempShot
		throw new UnsupportedOperationException();
	}

	public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
		return 0;
	}

	public boolean placeShipsAutomatically(int playerNr) {
		return false;
	}

	public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
		return false;
	}

	public boolean removeShip(int playerNr, int posX, int posY) {
		return false;
	}

	public boolean removeAllShips(int playerNr) {
		return false;
	}

	public boolean notifyWhenReady(int playerNr) {
		return false;
	}

	public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
		return null;
	}

	public ShotType fireShotOpponent(int playerNr) {
		return null;
	}

	public boolean startNewGame(int playerNr) {
		return false;
	}
}
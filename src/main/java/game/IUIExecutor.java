package game;


import models.Hit;
import models.Ship;
import models.ShipType;

public interface IUIExecutor {

	void placeTempShipLocal(ShipType shipType, int bowX, int bowY, boolean horizontal);
	void placeShipLocal(Ship ship);
	void removeShipLocal(Ship ship);

	/**
	 * Jij hebt dit geschoten op de tegenstander
	 * @param hit
	 */
	void fireShotLocal(Hit hit);

	/**
	 * De tegenstander schiet op jou
	 * @param hit
	 */
	void fireShotOpponent(Hit hit);

	void fireTempShotOpponent(int posX, int posY);
	void removeTempShotOpponent();

	void removeTempShipLocal(int posX, int posY);

	void gameReady(String opponentName);

	void fireReady();

	void gameEnded(String winner);
}
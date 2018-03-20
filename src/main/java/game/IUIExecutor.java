package game;


import models.Hit;
import models.Ship;
import models.ShipType;

public interface IUIExecutor {

	void placeTempShipLocal(ShipType shipType, int bowX, int bowY, boolean horizontal);
	void placeShipLocal(Ship ship);

	void fireShotLocal(Hit hit);
	void fireShotOpponent(Hit hit);

	void fireTempShotOpponent(int posX, int posY);
	void removeTempShotOpponent();

	void removeTempShipLocal(int posX, int posY);



}
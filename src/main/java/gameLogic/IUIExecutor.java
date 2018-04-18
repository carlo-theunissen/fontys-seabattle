package gameLogic;


import models.Hit;
import models.Ship;
import models.ShipType;

/**
 * De IUIExecutor is de communicatie tussen de front-end en de game logica.
 * Alles wat gebeurt in de game logica wat zou getoond moeten worden bij de speler, gebeurt via deze interface
 */
public interface IUIExecutor {

	/**
	 * Plaats een schip op de grid van de lokale speler
	 * @param ship
	 */
	void placeShipLocal(Ship ship);

	/**
	 * Verwijder een schip van het grid van de lokale speler
	 * @param ship
	 */
	void removeShipLocal(Ship ship);

	/**
	 * Jij hebt dit geschoten op de tegenstander
	 * @param hit
	 */
	void fireShotLocal(Hit hit);

	/**
	 * De tegenstander heeft dit op jouw grid geschoten
	 * @param hit
	 */
	void fireShotOpponent(Hit hit);

	/**
	 * Er is een tegenstander gevonden en het spel kan starten
	 * @param opponentName
	 */
	void gameReady(String opponentName);

	/**
	 * De lokale speler en de tegenstander zijn klaar met het plaatsen van schepen
	 * het schieten kan beginnen
	 */
	void fireReady();

	/**
	 * Het spel is gestopt omdat er iemand is gewonnen
	 * @param winner
	 */
	void gameEnded(String winner);
}
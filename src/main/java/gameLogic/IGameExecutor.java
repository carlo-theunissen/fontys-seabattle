package gameLogic;

import communication.PackageCommunication;
import gameLogic.exceptions.BoatInvalidException;
import gameLogic.exceptions.FireInvalidException;
import gameLogic.exceptions.PlayerNotTurnException;
import gameLogic.exceptions.PlayerStartException;
import models.Fire;
import models.Hit;
import models.Ship;

/**
 * Met deze interface kan je het game besturen
 */
public interface IGameExecutor {

    /**
     * Verkrijg de communicatielaag waarmee de game samenwerkt
     * @return
     */
    PackageCommunication getCommunication();

    /**
     * Verkrijg de frontend laag waarnaar de game de resultaten stuurt
     * @param GUIExecutor
     */
    void setGUIExecutor(IUIExecutor GUIExecutor);

    /**
     * Verkrijg de grid waar alle boten en schoten staan.
     * De schoten zijn die van de tegenstander die hij op jou heeft gedaan. De boten zijn van je zelf
     * @return
     */
    ShipGrid GetLocalGrid();

    /**
     * Zet de grootte van de grid
     * @param width
     * @param height
     */
    void setGridSize(int width, int height);


    /**
     * Verwijder een schip uit het spel, je verwijdert een schip die van je zelf is
     * @param ship
     * @throws PlayerStartException
     * @throws BoatInvalidException
     */
    void RemoveShip(Ship ship) throws PlayerStartException, BoatInvalidException;

    /**
     * Plaatst een schip in het spel, je plaatst je eigen schip
     * @param ship
     * @throws BoatInvalidException
     * @throws PlayerStartException
     */
    void PlaceShip(Ship ship) throws BoatInvalidException, PlayerStartException;

    /**
     * De tegenstander vuurt op ons grid, de "fire" parameter geeft de x en y coordinaten
     * We moeten reageren met een response, een hit object, zodat de tegenstander weet wat het resultaat is van zijn hit
     * @param fire
     */
    void OpponentFiresOnOurGrid(Fire fire);

    /**
     * Schiet op de grid van de tegenstander.
     * Je krijgt in "OpponentResponse" een reactie wat het resultaat is
     * @param fire
     * @throws PlayerStartException
     * @throws PlayerNotTurnException
     */
    void FireOnGridOpponent(Fire fire) throws PlayerStartException, PlayerNotTurnException;

    /**
     * Het resultaat van wanneer je schiet op je opponent
     * @param hit
     */
    void OpponentResponse(Hit hit);

    /**
     * Het spel kan starten en je speelt tegen de speler in "opponentName"
     * @param opponentName
     */
    void GameStart(String opponentName);

    /**
     * Allebei de tegenstanders zijn klaar met het plaatsen van boten
     */
    void StartFireState();

    /**
     * Geeft aan dat de locale speler, de huidge speler, klaar is met plaatsen van boten.
     * Als beiden spelers deze methode aanroepen wordt StartFireState() gestart.
     * @throws FireInvalidException
     * @throws PlayerStartException
     */
    void RequestFireState() throws FireInvalidException, PlayerStartException;

    /**
     * Registreert een nieuwe speler aan de server. Deze speler is klaar om te gaan spelen
     * @param playerName
     * @throws PlayerStartException
     */
    void PlayerReady(String playerName) throws PlayerStartException;
}

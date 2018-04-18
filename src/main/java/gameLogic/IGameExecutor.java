package gameLogic;

import communication.PackageCommunication;
import gameLogic.exceptions.BoatInvalidException;
import gameLogic.exceptions.FireInvalidException;
import gameLogic.exceptions.PlayerNotTurnException;
import gameLogic.exceptions.PlayerStartException;
import models.Fire;
import models.Hit;
import models.Ship;

public interface IGameExecutor {
    PackageCommunication getCommunication();

    void setGUIExecutor(IUIExecutor GUIExecutor);

    ShipGrid GetLocalGrid();

    void setGridSize(int width, int height);

    void RemoveShip(Ship ship) throws PlayerStartException, BoatInvalidException;

    void PlaceShip(Ship ship) throws BoatInvalidException, PlayerStartException;

    void OpponentFiresOnOurGrid(Fire fire);

    void FireOnGridOpponent(Fire fire) throws PlayerStartException, PlayerNotTurnException;

    void OpponentResponse(Hit hit);

    void GameStart(String opponentName);

    void StartFireState();

    void RequestFireState() throws FireInvalidException, PlayerStartException;

    void PlayerReady(String playerName) throws PlayerStartException;
}

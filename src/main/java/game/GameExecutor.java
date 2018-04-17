package game;

import communication.*;
import game.exceptions.*;
import helpers.CollideHelper;
import models.*;

/**
 * GameExecutor keeps track of the state of the game, all actions done in here are final
 */
public class GameExecutor {

    private enum GameState {
        /**
         * The game has started and the player is asked to fill in his name
         */
        STARTED,
        /**
         * Player can start placing or removing boats
         */
        EDIT_BOATS,

        /**
         * Player has placed all his boats and is now waiting for the opponent to finish
         */
        WAIT_OPPONENT_PLACED_BOATS,

        /**
         * It is now the player's turn to fire.
         */
        PLAYER_FIRE,

        /**
         * It is now the opponent's turn to fire
         */
        OPPONENT_FIRE,

        /**
         * Someone has won
         */
        WIN
    }

    private IUIExecutor GUIExecutor;
    private PackageCommunication communication;
    private GameState currentState;

    /**
     * The shipgrid is the grid from the local player.
     * On this grid all ships are placed. But it also stores all the shots the opponent did on our grid
     */
    private ShipGrid shipGrid;

    /**
     * The opponentgrid is the
     */
    private Grid opponentGrid;

    private String localPlayerName;
    private String opponentPlayerName;

    public GameExecutor(ICommunication communication) {
        this.communication = communication;
        communication.setLocalExecutor(this);
        shipGrid = new ShipGrid();
        currentState = GameState.STARTED;
        opponentGrid = new Grid();
    }

    public GameExecutor(PackageCommunication communication) {
        this.communication = communication;
        shipGrid = new ShipGrid();
        opponentGrid = new Grid();
    }

    public PackageCommunication getCommunication() {
        return communication;
    }

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        this.GUIExecutor = GUIExecutor;
    }

    public ShipGrid GetLocalGrid() {
        //prevent the grid being edited outside
        return new ImmortalShipGrid(shipGrid);
    }


    public void setGridSize(int width, int height) {
        shipGrid.setWidth(width);
        shipGrid.setHeight(height);

        opponentGrid.setWidth(width);
        opponentGrid.setHeight(height);
    }


    /**
     * Removes a ship from the grid.
     * @param ship
     */
    public void RemoveShip(Ship ship) throws PlayerStartException, BoatInvalidException {
        if (currentState != GameState.EDIT_BOATS) {
            throw new PlayerStartException("Invalid state");
        }

        if (shipGrid.removeShip(ship)) {
            communication.sendPackage(new RemoveShipPackage(ship));
            GUIExecutor.removeShipLocal(ship);
        }
    }

    public void PlaceShip(Ship ship) throws BoatInvalidException, PlayerStartException {

        if (currentState != GameState.EDIT_BOATS) {
            throw new PlayerStartException("Invalid state");
        }

        if (ship.getX() < 0 || ship.getY() < 0 || ship.getX() + (ship.getOrientation() == Orientation.Horizontal ? ship.getLength() : 0) > shipGrid.getWidth() || ship.getY() + (ship.getOrientation() == Orientation.Horizontal ? 0 : ship.getLength()) > shipGrid.getHeight()) {
            throw new BoatInvalidException("Valt buiten het grid");
        }

        for (int i = 0; i < ship.getLength(); i++) {
            if (new CollideHelper().getShip(ship.getX() + (ship.getOrientation() == Orientation.Horizontal ? i : 0), ship.getY() + i * (ship.getOrientation() == Orientation.Horizontal ? 0 : 1), shipGrid) != null) {
                throw new BoatInvalidException("Botst met iets");
            }
        }

        ship.setStatus(ShipStatus.Alive);
        for (Ship temp : shipGrid.getShips()) {
            if (temp.hashCode() == ship.hashCode()) {
                RemoveShip(temp);
                break;
            }
        }

        communication.sendPackage(new PlaceShipPackage(ship));

        shipGrid.setShip(ship);
        GUIExecutor.placeShipLocal(ship);
    }

    /**
     * Fire a shot on the local grid.
     * We should response with a hit
     *
     * @param fire
     */
    public void OpponentFiresOnOurGrid(Fire fire) {
        currentState = GameState.PLAYER_FIRE;
        Ship ship = new CollideHelper().getShip(fire.getX(), fire.getY(), shipGrid);
        Hit hit = new Hit(fire.getX(), fire.getY(), ship == null ? HitType.Miss : HitType.Collided);
        shipGrid.AddHit(hit);

        if (ship != null) {
            UpdateShipStatus(ship);
            hit.setSunk(ship.getStatus() == ShipStatus.Dead);
        }

        communication.sendPackage(new HitPackage(hit));
        GUIExecutor.fireShotLocal(hit);
        calculateWinner();
    }

    private void UpdateShipStatus(Ship ship) {
        int amountHit = 0;
        CollideHelper helper = new CollideHelper();
        for (Hit hit : shipGrid.getHits()) {
            if (helper.getShip(hit.getX(), hit.getY(), shipGrid) == ship) {
                amountHit++;
            }
            if (amountHit == ship.getLength()) {
                ship.setStatus(ShipStatus.Dead);
                return;
            }
        }
    }


    public void FireOnGridOpponent(Fire fire) throws PlayerStartException, PlayerNotTurnException {
        if (currentState == GameState.OPPONENT_FIRE) {
            throw new PlayerNotTurnException();
        }
        if (currentState != GameState.PLAYER_FIRE) {
            throw new PlayerStartException("Invalid state");

        }
        for (Hit hit : opponentGrid.getHits()) {
            if (hit.getX() == fire.getX() && hit.getY() == fire.getY()) {
                return;
            }
        }
        communication.sendPackage(new FirePackage(fire));
        currentState = GameState.OPPONENT_FIRE;
    }

    /**
     * We've fired on the grid of the opponent. In this method we get the result of that shot
     * @param hit
     */
    public void OpponentResponse(Hit hit) {
        opponentGrid.AddHit(hit);
        calculateWinner();
        GUIExecutor.fireShotOpponent(hit);
    }

    /**
     * Start the game
     * @param opponentName
     */
    public void GameStart(String opponentName) {
        opponentPlayerName = opponentName;
        GUIExecutor.gameReady(opponentName);
    }


    public void StartFireState() {
        currentState = GameState.PLAYER_FIRE;
        GUIExecutor.fireReady();
    }

    /**
     * TODO: Check als je wel eerst "PlayerReady" heb aangeroepen en dat al de schepen wel geplaatst zijn
     * -- IS DONE --
     */
    public void RequestFireState() throws FireInvalidException, PlayerStartException {
        if (currentState == GameState.EDIT_BOATS && shipGrid.getShips().size() == 5) {
            currentState = GameState.WAIT_OPPONENT_PLACED_BOATS;
            communication.sendPackage(new RequestFireReady());
        } else if (currentState != GameState.EDIT_BOATS) {
            throw new PlayerStartException("Invalid state");
        } else {
            throw new FireInvalidException("Niet alle schepen zijn geplaatst");
        }
    }

    /**
     * Call this method when the player is ready to start
     */
    public void PlayerReady(String playerName) throws PlayerStartException {
        if (currentState == GameState.STARTED && !playerName.isEmpty()) {
            currentState = GameState.EDIT_BOATS;
            communication.sendPackage(new ReadyPackage(playerName));
            localPlayerName = playerName;
        } else if (currentState != GameState.STARTED) {
            throw new PlayerStartException("Playerstart is al een keer aangeroepen");
        } else {
            throw new PlayerStartException("Player is nog niet geregistreerd");
        }
    }


    private void calculateWinner() {
        boolean localPlayerDead = true;
        int totalPossibleHits = 5 + 4 + 3 + 3 + 2;
        int opponentHits = 0;

        for (Ship ship : shipGrid.getShips()) {


            if (ship.getStatus() != ShipStatus.Dead) {
                localPlayerDead = false;//found a contradiction
                break;
            }
        }

        if (localPlayerDead) {
            currentState = GameState.WIN;
            GUIExecutor.gameEnded(opponentPlayerName);
        }

        for (Hit hit : opponentGrid.getHits()) {
            if (hit.getHitType() == HitType.Collided) {
                opponentHits++;
            }
        }

        if (opponentHits >= totalPossibleHits) {
            currentState = GameState.WIN;
            GUIExecutor.gameEnded(localPlayerName);
        }
    }
}
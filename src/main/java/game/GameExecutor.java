package game;

import communication.*;
import game.exceptions.*;
import helpers.CollideHelper;
import models.*;

/**
 * GameExecutor keeps track of the state of the game, all actions done in here are final
 */
public class GameExecutor {

    private IUIExecutor GUIExecutor;
    private PackageCommunication communication;
    private boolean playerStartAccessed = false;
    private boolean isPlayerTurn = true;


    public GameExecutor(ICommunication communication){
        this.communication = communication;
        communication.setLocalExecutor(this);

        shipGrid = new ShipGrid();
        opponentGrid = new Grid();
    }

    public GameExecutor(PackageCommunication communication){
        this.communication = communication;

        shipGrid = new ShipGrid();
        opponentGrid = new Grid();
    }
    public PackageCommunication getCommunication() {
        return communication;
    }

    private ShipGrid shipGrid;
    private Grid opponentGrid;

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        this.GUIExecutor = GUIExecutor;
    }

    public ShipGrid GetLocalGrid() {
	    //prevent the grid being edited outside
		return new ImmortalShipGrid(shipGrid);
	}
	public Grid GetopponentGrid(){
        return opponentGrid;
    }

	public void setGridSize(int width, int height){
        shipGrid.setWidth(width);
        shipGrid.setHeight(height);

        opponentGrid.setWidth(width);
        opponentGrid.setHeight(height);
    }


    /**
     * Removes a ship from the grid.
     * TODO: let communication know about this
     * TODO: Check als de player niet al heeft bevestigd dat hij klaar is met schepen plaatsen.
     * @param ship
     */
	public void RemoveShip(Ship ship){
        if(shipGrid.removeShip(ship)){
            GUIExecutor.removeShipLocal(ship);
        }
    }

	/**
	 * TODO: Check als de player niet al heeft bevestigd dat hij klaar is met schepen plaatsen.
	 * @param ship
	 */
	public void PlaceShip(Ship ship) throws BoatInvalidException, PlayerNotStartedException {

        if(!playerStartAccessed) {
            throw new PlayerNotStartedException();
        }
        //todo maak het verder af

	    if(ship.getX() < 0 || ship.getY() < 0 || ship.getX() + (ship.getOrientation() == Orientation.Horizontal ? ship.getLength() : 0) > shipGrid.getWidth() || ship.getY() + (ship.getOrientation() == Orientation.Horizontal ? 0 : ship.getLength() ) > shipGrid.getHeight() ){
	        throw new BoatInvalidException("Valt buiten het grid");
        }

	    for(int i = 0; i < ship.getLength(); i++) {
            if(new CollideHelper().getShip(ship.getX() +(ship.getOrientation() == Orientation.Horizontal ? i : 0), ship.getY() + i * (ship.getOrientation() == Orientation.Horizontal ? 0 : 1), shipGrid) != null){
                throw new BoatInvalidException("Botst met iets");
            }
        }

	    for(Ship temp : shipGrid.getShips()){
	        if(temp.hashCode() == ship.hashCode()){
	            RemoveShip(temp);
	            break;
            }
        }
		shipGrid.setShip(ship);
        GUIExecutor.placeShipLocal(ship);
	}

    /**
     * Fire a shot on the local grid.
     * We should response with a hit
     * @param fire
     */
	public void FireShot(Fire fire) {

		Ship ship = new CollideHelper().getShip(fire.getX(), fire.getY(), shipGrid);
		Hit hit = new Hit(fire.getX(), fire.getY(), ship == null ? HitType.Miss : HitType.Collided);
        shipGrid.AddHit(hit);

        if(ship != null) {
            UpdateShipStatus(ship);
            hit.setSunk(ship.getStatus() == ShipStatus.Dead);
        }

		communication.sendPackage(new HitPackage(hit));
        GUIExecutor.fireShotLocal(hit);
	}

	private void UpdateShipStatus(Ship ship){
	    int amountHit = 0;
	    CollideHelper helper = new CollideHelper();
        for(Hit hit : shipGrid.getHits()){
            if(helper.getShip(hit.getX(), hit.getY(), shipGrid) == ship){
                amountHit++;
            }
            if(amountHit == ship.getLength()){
                ship.setStatus(ShipStatus.Dead);
                return;
            }
        }
    }

    /**
     * Fire on the grid of the opponent
     * @param fire
     */
	public boolean FireOpponent(Fire fire) throws PlayerNotStartedException, PlayerNotTurnException {
	    if(!playerStartAccessed) {
	        throw new PlayerNotStartedException();
        }
        if(!isPlayerTurn){
            throw new PlayerNotTurnException();
        }
        for (Hit hit : opponentGrid.getHits()) {
            if (hit.getX() == fire.getX() && hit.getY() == fire.getY()) {
                return false;
            }
        }
        communication.sendPackage(new FirePackage(fire));
        isPlayerTurn = false;

        return true;
    }

    /**
     * The response of the opponent
     * @param hit
     */
	public void FireResponse(Hit hit){
        opponentGrid.AddHit(hit);
        GUIExecutor.fireShotOpponent(hit);
        isPlayerTurn = true;
    }


    public void GameReady(String opponentName){
        GUIExecutor.gameReady(opponentName);
    }

    public void FireReady(){
        GUIExecutor.fireReady();
    }

    /**
     * TODO: Check als je wel eerst "PlayerStart" heb aangeroepen en dat al de schepen wel geplaatst zijn
     * -- IS DONE --
     */
    public void RequestFireReady() throws FireInvalidException, PlayerNotStartedException {
        if (playerStartAccessed && shipGrid.getShips().size() == 5) {
            communication.sendPackage(new RequestFireReady());
        } else if (!playerStartAccessed){
            throw new PlayerNotStartedException();
        } else {
            throw new FireInvalidException("Niet alle schepen zijn geplaatst");
        }
    }

    /**
     * Call this method when the player is ready to start
     * TODO: Check als de playernaam wel geldig is en als je niet eerder "PlayerStart" hebt aangeroepen
     * -- IS DONE --
     */
    public void PlayerStart(String playerName) throws PlayerStartException {
        if (!playerStartAccessed && !playerName.isEmpty()) {
            communication.sendPackage(new ReadyPackage(playerName));
            playerStartAccessed = true;
        } else if (playerStartAccessed){
            throw new PlayerStartException("Playerstart is al een keer aangeroepen");
        } else {
            throw new PlayerStartException("Playername is empty");
        }
    }

    //todo: afhandlen
    public void ServerException(GameException exception){
        exception.getMessage();
    }
}
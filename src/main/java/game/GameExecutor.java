package game;

import communication.FirePackage;
import communication.HitPackage;
import communication.ICommunication;
import communication.StartPackage;
import helpers.CollideHelper;
import models.*;

/**
 * GameExecutor keeps track of the state of the game, all actions done in here are final
 */
public class GameExecutor {

    private IUIExecutor GUIExecutor;
    private ICommunication communication;
    public GameExecutor(ICommunication communication){
        this.communication = communication;
        this.communication.setLocalExecutor(this);

        shipGrid = new ShipGrid();
        opponentGrid = new Grid();
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
     * @param ship
     */
	public void RemoveShip(Ship ship){
        if(shipGrid.removeShip(ship)){
            GUIExecutor.removeShipLocal(ship);
        }
    }

	/**
	 *
	 * @param ship
	 */
	public void PlaceShip(Ship ship) throws Exception {

	    if(ship.getX() < 0 || ship.getY() < 0 || ship.getX() + (ship.getOrientation() == Orientation.Horizontal ? ship.getLength() : 0) > shipGrid.getWidth() || ship.getY() + (ship.getOrientation() == Orientation.Horizontal ? 0 : ship.getLength() ) > shipGrid.getHeight() ){
	        throw new Exception("Valt buiten het grid");
        }

	    for(int i = 0; i < ship.getLength(); i++) {
            if(new CollideHelper().getShip(ship.getX() +(ship.getOrientation() == Orientation.Horizontal ? i : 0), ship.getY() + i * (ship.getOrientation() == Orientation.Horizontal ? 0 : 1), shipGrid) != null){
                throw new Exception("Botst met iets");
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
	public boolean FireOpponent(Fire fire){
        for(Hit hit : opponentGrid.getHits()){
            if(hit.getX() == fire.getX() && hit.getY() == fire.getY()){
                return false;
            }
        }
        communication.sendPackage(new FirePackage(fire));
        return true;
    }

    /**
     * The response of the opponent
     * @param hit
     */
	public void FireResponse(Hit hit){
        opponentGrid.AddHit(hit);
        GUIExecutor.fireShotOpponent(hit);
    }


    public void GameReady(String opponentName){
        GUIExecutor.gameReady(opponentName);
    }

    /**
     * Call this method when the player is ready to start
     */
    public void PlayerStart(String playerName){
        communication.sendPackage(new StartPackage(playerName));
    }

}
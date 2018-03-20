package game;

import communication.FirePackage;
import communication.HitPackage;
import communication.ICommunication;
import helpers.CollideHelper;
import models.Fire;
import models.Hit;
import models.HitType;
import models.Ship;

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

	/**
	 *
	 * @param ship
	 */
	public void PlaceBoat(Ship ship) {
		shipGrid.setShip(ship);
	}

    /**
     * Fire a shot on the local grid.
     * We should response with a hit
     * @param fire
     */
	public void FireShot(Fire fire) {
		Ship ship = new CollideHelper().getShip(fire.getX(), fire.getY(), shipGrid);
		communication.sendPackage(new HitPackage(new Hit(fire.getX(), fire.getY(), ship == null ? HitType.Miss : HitType.Collided)));
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
    }

}
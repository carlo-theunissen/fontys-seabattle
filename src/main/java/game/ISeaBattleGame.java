/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


/**
 * Interface provided by the sea battle game to the graphical user interface.
 * @author Nico Kuijpers
 */
public interface ISeaBattleGame {
    
    /**
     * Register player with given name.
     * The name of the player must be unique.
     * @param name              Name of the player to be registered
     * @param application       Reference to application of player
     * @param singlePlayerMode  Single-player (true) or multi-player (false) mode
     * @return player number 0 or 1 when name is not already registered and number
     * of participants does not exceed 1 for single-player mode or 2 for
     * multi-player mode, otherwise -1 will be returned.
     */
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode);
    
    /**
     * Place ships automatically.
     * Ships that are already placed will be removed. All ships are placed
     * successfully if they all fit entirely within the grid and have no
     * overlap with each other.
     * @param playerNr identification of player for which ships will be placed
     * @return true if all ships are placed successfully, false otherwise.
     */
    public boolean placeShipsAutomatically(int playerNr);

    /**
     * Place ship of given type.
     * A new ship will be placed with its bow at the given coordinates.
     * If horizontal = true, the stern will be placed to the right of the bow.
     * If horizontal = false, the stern will be placed below the bow.
     * A ship is placed successfully if it fits entirely within the grid and
     * has no overlap with other ships.
     * @param playerNr  identification of player for which ship will be placed
     * @param shipType  type of ship to be placed
     * @param bowX      x-coordinate of bow
     * @param bowY      y-coordinate of bow
     * @param horizontal indicate whether ship will placed horizontally or vertically
     * @return true if ship can be placed successfully, false otherwise.
     */
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal);

    /**
     * Remove the ship that is placed at the square with coordinates (posX, posY).
     * @param playerNr identification of player for which ship will be removed
     * @param posX     x-coordinate of square where ship was placed
     * @param posY     y-coordinate of square where ship was placed
     * @return true if a ship was successfully removed, false otherwise.
     */
    public boolean removeShip(int playerNr, int posX, int posY);
    
    /**
     * Remove all ships that are placed.
     * @param playerNr  identification of player for which ships will be removed
     * @return true if all ships were successfully removed, false otherwise.
     */
    public boolean removeAllShips(int playerNr);

    /**
     * Notify that the player is ready to play the game, i.e., all ships have
     * been placed.
     * @param playerNr identification of player who is ready to play the game
     * @return true if all ships have been placed, false otherwise.
     */
    public boolean notifyWhenReady(int playerNr);

    /**
     * Fire a shot at the opponent's square with given coordinates.
     * The result of the shot will be one of the following:
     * MISSED  - No ship was hit
     * HIT     - A ship was hit
     * SUNK    - A ship was sunk
     * ALLSUNK - All ships are sunk
     * @param playerNr identification of player who fires.
     * @param posX     x-coordinate of square
     * @param posY     y-coordinate of square
     * @return result of the shot.
     */
    public ShotType fireShotPlayer(int playerNr, int posX, int posY);
    
    /**
     * Let the opponent fire a shot at the player's square.
     * This method is used in the single-player mode.
     * A shot is fired by the opponent using some AI strategy.
     * The result of the shot will be one of the following:
     * MISSED  - No ship was hit
     * HIT     - A ship was hit
     * SUNK    - A ship was sunk
     * ALLSUNK - All ships are sunk
     * @param playerNr identification of the player for which the opponent
     *                 will fire a shot
     * @return result of the shot
     */
    public ShotType fireShotOpponent(int playerNr);
    
    /**
     * Start a new game.
     * Remove all ships and unregister the players.
     * @param playerNr
     * @return true if a new game is started successfully
     */
    public boolean startNewGame(int playerNr);
}

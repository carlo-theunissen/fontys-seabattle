package client.userInterface;

import communication.SinglePlayerCommunication;
import gameLogic.GameExecutor;
import gameLogic.GameManager;
import gameLogic.IGameExecutor;
import gameLogic.IUIExecutor;
import gameLogic.exceptions.FireInvalidException;
import gameLogic.exceptions.PlayerNotTurnException;
import gameLogic.exceptions.PlayerStartException;
import models.*;

public class SinglePlayerGame extends BaseGame implements ISeaBattleGame {

    private IGameExecutor opponentPlayer;

    public void setGUIExecutor(IUIExecutor GUIExecutor) {
        opponentPlayer.setGUIExecutor( new EmptySeaBattleGUI() );
        localPlayer.setGUIExecutor( GUIExecutor );
    }

    public SinglePlayerGame(){
        GameManager game = new GameManager();
        SinglePlayerCommunication communcationFromPlayerToAI = new SinglePlayerCommunication();
        localPlayer = new GameExecutor(communcationFromPlayerToAI);

        SinglePlayerCommunication communicationFromAIToPlayer = new SinglePlayerCommunication();
        communicationFromAIToPlayer.setOtherPlayer(localPlayer);
        communicationFromAIToPlayer.setGameManager(game);

        opponentPlayer = new GameExecutor(communicationFromAIToPlayer);

        communcationFromPlayerToAI.setOtherPlayer(opponentPlayer);
        communcationFromPlayerToAI.setGameManager(game);


        localPlayer.setGridSize(10,10);
        opponentPlayer.setGridSize(10,10);
    }

    @Override
    public IGameExecutor getPlayer(int playerNr){
        IGameExecutor player;
        if (playerNr == 0) {
            player = localPlayer;
        } else if(playerNr == 1){
            player = opponentPlayer;
        } else {
            throw new IllegalArgumentException("Playernumber cannot be more then 1 or be negative");
        }
        return player;
    }

    // TODO: CARLO FIX DIT, INVALID STATE EXCEPTIONS NA JOUW COMMIT FIX NU PLS
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {

        try {
            opponentPlayer.PlayerReady("AI");
        } catch (PlayerStartException e) {
            enhancedGUI.showMessage(e.getMessage());
        }
        PlaceShipsAI();
        try {
            opponentPlayer.RequestFireState();
        } catch (FireInvalidException e) {
            String test = e.getMessage();
            enhancedGUI.showMessage(test);
        } catch (PlayerStartException e) {
            e.printStackTrace();
        }

        try {
            localPlayer.PlayerReady(name);
        } catch (PlayerStartException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void PlaceShipsAI(){
        placeShip(1, ShipType.AIRCRAFTCARRIER, 0,0, true);
        placeShip(1, ShipType.BATTLESHIP, 0,1, true);
        placeShip(1, ShipType.CRUISER, 0,2, true);
        placeShip(1, ShipType.MINESWEEPER, 0,3, true);
        placeShip(1, ShipType.SUBMARINE, 0,4, true);
    }

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
    public ShotType fireShotOpponent(int playerNr) {
        if(playerNr != 0){
            return ShotType.MISSED;
        }

         // TODO: Check de return waarden van opponentPlayer.FireOpponent  als die "FALSE" is, kan je daar niet meer schieten
         // TODO: dus moet je een nieuwe x,y waarden pakken
        try {
            opponentPlayer.FireOnGridOpponent(new Fire((int)(Math.random()* 10), (int)(Math.random()* 10)));
        } catch (PlayerStartException e) {
            e.printStackTrace();
        } catch (PlayerNotTurnException e) {
            e.printStackTrace();
        }
        return ShotType.MISSED;
    }

}

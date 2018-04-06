package communication;

import java.util.*;

/**
 * This class is used for keeping track of the action that both players have done.
 * For example when they are both ready with placing ships.
 */
class LocalSinglePlayerManager {
    private LinkedHashMap<SinglePlayerCommunication, String> playerCommunications;
    private int fireReady;
    LocalSinglePlayerManager(){
        playerCommunications = new LinkedHashMap<>();
    }

    public void registerPlayer(SinglePlayerCommunication player, String name){
        if(playerCommunications.size() < 2) {
            playerCommunications.put(player, name);
        }
        if(playerCommunications.size() == 2){
            broadcastGameStart();
        }
    }

    public void requestFireReady(){
        fireReady++;
        if(fireReady == 2){
            broadcastFireReady();
        }
    }

    private void broadcastFireReady(){
        ArrayList<SinglePlayerCommunication> players =  new ArrayList<>( playerCommunications.keySet());
        players.get(1).sendPackage(new FireReady());
        players.get(0).sendPackage(new FireReady());
    }

    private void broadcastGameStart(){
        ArrayList<SinglePlayerCommunication> players =  new ArrayList<>( playerCommunications.keySet());
        ArrayList<String> playerName =  new ArrayList<>( playerCommunications.values());
        players.get(1).sendPackage(new StartPackage( playerName.get(0)));
        players.get(0).sendPackage(new StartPackage( playerName.get(1)));

    }


}

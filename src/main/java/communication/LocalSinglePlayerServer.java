package communication;

import java.util.*;

class LocalSinglePlayerServer {
    private LinkedHashMap<SinglePlayerCommunication, String> playerCommunications;

    LocalSinglePlayerServer(){
        playerCommunications = new LinkedHashMap<>();
    }

    public void register(SinglePlayerCommunication player, String name){
        if(playerCommunications.size() < 2) {
            playerCommunications.put(player, name);
        }
        if(playerCommunications.size() == 2){
            broadcastGameStart();
        }
    }

    private void broadcastGameStart(){
        ArrayList<SinglePlayerCommunication> players =  new ArrayList<>( playerCommunications.keySet());
        ArrayList<String> playerName =  new ArrayList<>( playerCommunications.values());
        players.get(1).sendPackage(new StartPackage( playerName.get(1)));
        players.get(0).sendPackage(new StartPackage( playerName.get(0)));

    }


}

package websocketServer;

import client.userInterface.EmptySeaBattleGUI;
import communication.MultiplayerServerToLocalCommunication;
import gameLogic.GameExecutor;
import gameLogic.IGameExecutor;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameExecutorCollection {
    private final Map<Session, IGameExecutor> executors;

    public GameExecutorCollection() {
        executors = new LinkedHashMap<>();
    }

    public IGameExecutor getExecutor(Session session){
        return executors.get(session);
    }
    public IGameExecutor getOpponent(Session currentPlayer){
        if(executors.size() < 2){
            return null;
        }
        ArrayList<Session> sessions =  new ArrayList<>( executors.keySet());
        ArrayList<IGameExecutor> games =  new ArrayList<>( executors.values());
        if(sessions.get(0) == currentPlayer){
            return games.get(1);
        }
        return games.get(0);
    }

    private void setOpponents(){
        ArrayList<IGameExecutor> games =  new ArrayList<>( executors.values());
        ArrayList<Session> sessions =  new ArrayList<>( executors.keySet());
        ((MultiplayerServerToLocalCommunication) games.get(0).getCommunication()).setOpponentEndpoint(sessions.get(1).getBasicRemote());
        ((MultiplayerServerToLocalCommunication) games.get(1).getCommunication()).setOpponentEndpoint(sessions.get(0).getBasicRemote());
    }

    public void createNewExecutor(Session session) throws Exception {
        if(executors.size() >= 2){
            throw new Exception("Too many players");
        }
        if(!executors.containsKey(session)){
            IGameExecutor executor = new GameExecutor(new MultiplayerServerToLocalCommunication(session.getBasicRemote()));
            executor.setGridSize(10,10);
            executor.setGUIExecutor(new EmptySeaBattleGUI());
            executors.put(session, executor);
        }
        if(executors.size() == 2){
            setOpponents();
        }
    }
}

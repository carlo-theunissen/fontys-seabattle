package websocketServer;


import gameLogic.GameManager;
import gameLogic.IGameExecutor;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, copied from github, adapted by Marcel Koonen
 */

@ServerEndpoint(value = "/game/")
public class EventServerSocket {


    private static final GameExecutorCollection collection = new GameExecutorCollection();
    private static final ServerIntroLayer serverIntro = new ServerIntroLayer();
    
    public EventServerSocket() {
        synchronized (serverIntro) {
            if (serverIntro.getGameManager() == null) {
                serverIntro.setGameManager(new GameManager());
            }
        }
    }

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("CONNECT!");
        try {
            collection.createNewExecutor(session);
        } catch (Exception e) {
            try {
                session.close();
            } catch (IOException ignored) {}
        }
    }
    @OnMessage
    public void onText(String message,Session session) {
        System.out.println(message);
        IGameExecutor executor = collection.getExecutor(session);
        serverIntro.postNewMessage(executor, collection.getOpponent(session), message);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        //sessions.remove(session);
    }
    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }
}
package websocketServer;

import game.GameExecutor;

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

@ServerEndpoint(value = "/wstest/")
public class EventServerSocket {


    private final GameExecutorCollection collection;
    private final ServerIntroLayer serverIntro;
    
    public EventServerSocket() {
        collection = new GameExecutorCollection();
        serverIntro = new ServerIntroLayer();
    }

    @OnOpen
    public void onConnect(Session session) {
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
        GameExecutor executor = collection.getExecutor(session);
        serverIntro.postNewMessage(executor, message);
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
    public void broadcast(String s) {

        System.out.println("[Broadcast] { " + s + " } to:");
        for(Session session : sessions) {
            try {
                session.getBasicRemote().sendText(s);
                System.out.println("\t\t >> Client associated with server side session ID: " + session.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[End of Broadcast]");

    }
}
package communication;

import models.Hit;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

public class MultiplayerLocalToServerCommunication extends BaseCommunication implements ICommunication {
    private MultiplayerSerialHelper helper;
    private final Session session;

    public MultiplayerLocalToServerCommunication() throws IOException, DeploymentException {
        helper = new MultiplayerSerialHelper();

        URI uri = URI.create("ws://localhost:8080/game/");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        session = container.connectToServer(new EventClientSocket(),uri);
    }

    @Override
    public void sendPackage(CommunicationPackage communicationPackage) {
        try {
            switch (communicationPackage.getAction()) {
                case Ready:
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
                case Start:
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
                case Fire:
                    //we vuren op de tegenstanders veld
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
                case HitResponse:
                    //hitreponse wordt door de server afghandeld
                    break;
                case FireReadyRespone:
                    //wordt door de server afgehandeld
                    break;
                case RequestFireReady:
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
                case PlaceShip:
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
                case RemoveShip:
                    session.getBasicRemote().sendText(helper.serializePackage(communicationPackage));
                    break;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleIncomingPackage(CommunicationPackage communicationPackage){
        switch (communicationPackage.getAction()) {
            case Ready:
                //server will never send us a ready
                break;
            case Start:
                getLocalExecutor().GameStart(communicationPackage.getData());
                break;
            case Fire:
                //server does not send us a fire
                break;
            case HitResponse:
                Hit hit = HitPackage.unserialize(communicationPackage.getData());
                getLocalExecutor().FireResponse(hit);
                break;
            case FireReadyRespone:
                getLocalExecutor().FireReady();
                break;
            case RequestFireReady:
                //request fire ready is sent to the client to us. We don't have to respond
                break;
            case PlaceShip:
                //client handelt dit zelf af
                break;
            case RemoveShip:
                //client handelt dit zelf af
                break;
        }
    }



    @ClientEndpoint
    public class EventClientSocket{
        @OnOpen
        public void onWebSocketConnect() {
            System.out.println("[Connected]");
        }
        @OnMessage
        public void onWebSocketText(String message) {
            System.out.println("[Received]: " + message);
            handleIncomingPackage(helper.unserializePackage(message));
        }
        @OnClose
        public void onWebSocketClose(CloseReason reason) {
            System.out.println("[Closed]: " + reason);
        }
        @OnError
        public void onWebSocketError(Throwable cause) {
            System.out.println("[ERROR]: " + cause.getMessage());
        }
    }
}

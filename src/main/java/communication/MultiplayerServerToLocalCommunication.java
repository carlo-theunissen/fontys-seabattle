package communication;

import javax.websocket.RemoteEndpoint;
import java.io.IOException;

public class MultiplayerServerToLocalCommunication implements PackageCommunication {

    private MultiplayerSerialHelper helper;
    private final RemoteEndpoint.Basic playerEndpoint;
    private RemoteEndpoint.Basic opponentEndpoint;

    public MultiplayerServerToLocalCommunication(RemoteEndpoint.Basic player) {
        this.playerEndpoint = player;
        helper = new MultiplayerSerialHelper();
    }

    public void setOpponentEndpoint(RemoteEndpoint.Basic opponentEndpoint) {
        this.opponentEndpoint = opponentEndpoint;
    }

    @Override
    public void sendPackage(CommunicationPackage communicationPackage) {
        try {
            switch (communicationPackage.getAction()) {
                case Ready:
                    //don't do anything. The client has sent to us he's ready. We don't have to replay that we are.
                    break;
                case Start:
                    playerEndpoint.sendText(helper.serializePackage(communicationPackage));
                    break;
                case Fire:
                    //verstuur de fire naar de eigen speler
                    playerEndpoint.sendText(helper.serializePackage(communicationPackage));
                    break;
                case HitResponse:
                    //verstuur de hitresponse naar de tegenstander
                    opponentEndpoint.sendText(helper.serializePackage(communicationPackage));
                    break;
                case FireReadyRespone:
                    //laat eigen speler weten dat hij kan beginnen met vuren
                    playerEndpoint.sendText(helper.serializePackage(communicationPackage));
                    break;
                case RequestFireReady:
                    //request fire ready is sent to the client to us. We don't have to respond
                    break;
                case PlaceBoat:
                    break;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

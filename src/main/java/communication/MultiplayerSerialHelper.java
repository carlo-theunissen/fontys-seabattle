package communication;

public class MultiplayerSerialHelper  {
    public CommunicationPackage unserializePackage(String data){
        String split[] = data.split("]");
        switch (split[0]) {
            case "ready":
                return  createPackage(CommunicationAction.Ready, split[1]);
            case "start":
                return  createPackage(CommunicationAction.Start, split[1]);
            case "fire":
                return  createPackage(CommunicationAction.Fire, split[1]);
            case "hit":
                return  createPackage(CommunicationAction.HitResponse, split[1]);
            case "fireReady":
                return  createPackage(CommunicationAction.FireReadyRespone, split[1]);
            case "requestFire":
                return  createPackage(CommunicationAction.RequestFireReady, split[1]);
            case "placeShip":
                return  createPackage(CommunicationAction.PlaceShip, split[1]);
            case "removeShip":
                return  createPackage(CommunicationAction.RemoveShip, split[1]);
        }
        return null;
    }

    private CommunicationPackage createPackage(CommunicationAction action, String data){
        return new CommunicationPackage(action, data);
    }

    protected String serializePackage(CommunicationPackage communication){
        switch (communication.getAction()) {
            case Ready:
                return ("ready]" + communication.getData());
            case Start:
                return ("start]" + communication.getData());
            case Fire:
                return ("fire]" + communication.getData());
            case HitResponse:
                return ("hit]" + communication.getData());
            case FireReadyRespone:
                return ("fireReady]" + communication.getData());
            case RequestFireReady:
                return ("requestFire]" + communication.getData());
            case PlaceShip:
                return ("placeShip]" + communication.getData());
            case RemoveShip:
                return ("removeShip]" + communication.getData());
        }
        return "";
    }
}

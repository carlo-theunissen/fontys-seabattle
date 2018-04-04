package communication;

public class ReadyPackage extends CommunicationPackage {
    public ReadyPackage(String playerName) {
        super(CommunicationAction.Fire, serialize(playerName));
    }
    private static String serialize(String playerName){
        return playerName;
    }
    public static String unserialize(String data){
        return data;
    }
}

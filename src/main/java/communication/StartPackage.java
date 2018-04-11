package communication;

/**
 * start het spel
 */
public class StartPackage extends CommunicationPackage {
    public StartPackage(String opponentPlayerName) {
        super(CommunicationAction.Start, serialize(opponentPlayerName));
    }
    private static String serialize(String opponentPlayerName){
        return opponentPlayerName;
    }
    public static String unserialize(String data){
        return data;
    }
}

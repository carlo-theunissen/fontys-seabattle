package communication;

public class RequestFireReady extends CommunicationPackage {
    public RequestFireReady() {
        super(CommunicationAction.RequestFireReady, serialize());
    }
    private static String serialize(){
        return "";
    }
    public static String unserialize(String data){
        return "";
    }
}

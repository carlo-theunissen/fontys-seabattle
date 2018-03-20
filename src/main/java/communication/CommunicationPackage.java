package communication;

abstract class CommunicationPackage {
    private final CommunicationAction action;
    private final String data;
    protected CommunicationPackage(CommunicationAction action, String data){
        this.action = action;
        this.data = data;
    }

    public CommunicationAction getAction() {
        return action;
    }

    public String getData() {
        return data;
    }
}

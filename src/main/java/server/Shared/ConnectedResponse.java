package server.Shared;

public class ConnectedResponse {

    private int connected = 0;
    private ExtraInfoConnectedResponse extraInfo;


    public int getConnected() {
        return connected;
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    public ExtraInfoConnectedResponse getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ExtraInfoConnectedResponse extraInfo) {
        this.extraInfo = extraInfo;
    }
}


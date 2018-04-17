package server.Shared;

public class ExtraInfoStatusResponse {

    private String connections = "/server/clients/connected";

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }
}


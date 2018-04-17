package server;

public class ServerStatusContainer {
    enum ServerStatus{
        OPEN,
        CLOSED
    }
    private static ServerStatus status = ServerStatus.OPEN;

    private static int connected = 0;

    public static int getConnected() {
        return connected;
    }

    public static void setConnected(int connected) {
        ServerStatusContainer.connected = connected;
    }

    public static ServerStatus getStatus() {
        return status;
    }

    public static void setStatus(ServerStatus status) {
        ServerStatusContainer.status = status;
    }
}

package communication;

import game.GameExecutor;

public interface ICommunication {
    void sendPackage(CommunicationPackage communicationPackage);
    void setLocalExecutor(GameExecutor localExecutor);
}

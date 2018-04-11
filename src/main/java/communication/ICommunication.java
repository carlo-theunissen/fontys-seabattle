package communication;

import game.GameExecutor;

public interface ICommunication  extends PackageCommunication{
    void setLocalExecutor(GameExecutor localExecutor);
}

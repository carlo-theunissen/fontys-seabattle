package communication;


import gameLogic.IGameExecutor;

public interface ICommunication  extends PackageCommunication{
    void setLocalExecutor(IGameExecutor localExecutor);
}

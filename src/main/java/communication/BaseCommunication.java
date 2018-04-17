package communication;

import gameLogic.IGameExecutor;

abstract public class BaseCommunication {
    private IGameExecutor localExecutor;

    IGameExecutor getLocalExecutor() {
        return localExecutor;
    }

    public void setLocalExecutor(IGameExecutor localExecutor) {
        this.localExecutor = localExecutor;
    }
}

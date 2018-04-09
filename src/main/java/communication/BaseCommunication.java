package communication;

import game.GameExecutor;

abstract public class BaseCommunication {
    private GameExecutor localExecutor;

    GameExecutor getLocalExecutor() {
        return localExecutor;
    }

    public void setLocalExecutor(GameExecutor localExecutor) {
        this.localExecutor = localExecutor;
    }
}

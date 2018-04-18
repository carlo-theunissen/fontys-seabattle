package communication;


import gameLogic.IGameExecutor;

/**
 * Dit is de communicatie laag waarmee je pakketten kan versturen.
 * Een communicatie laag wilt ook de resultaten terug geven, die doet hij met en IGameExecutor
 */
public interface ICommunication  extends PackageCommunication{

    /**
     * Zet de IGameExecutor waarnaar de communicatie laag de resultaten stuurt
     * @param localExecutor
     */
    void setLocalExecutor(IGameExecutor localExecutor);
}

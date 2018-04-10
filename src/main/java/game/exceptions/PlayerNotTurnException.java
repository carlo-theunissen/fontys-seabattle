package game.exceptions;

public class PlayerNotTurnException extends GameException {
    public PlayerNotTurnException() {
        super("Player is not in turn");
    }
}

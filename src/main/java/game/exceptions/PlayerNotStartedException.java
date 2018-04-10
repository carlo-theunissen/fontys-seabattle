package game.exceptions;

public class PlayerNotStartedException extends GameException {
    public PlayerNotStartedException() {
        super("Player is not ready");
    }
}

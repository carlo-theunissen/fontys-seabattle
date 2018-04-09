package game.exceptions;

public abstract class GameException extends Exception {
    public GameException(String text) {
        super(text);
    }
}

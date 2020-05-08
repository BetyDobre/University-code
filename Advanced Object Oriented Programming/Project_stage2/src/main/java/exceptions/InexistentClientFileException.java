package exceptions;

public class InexistentClientFileException extends RuntimeException{
    public InexistentClientFileException() {
        super("CLIENTS file doesn't exist!");
    }
}

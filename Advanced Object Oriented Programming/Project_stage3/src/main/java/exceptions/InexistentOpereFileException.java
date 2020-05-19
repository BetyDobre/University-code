package exceptions;

public class InexistentOpereFileException extends RuntimeException{
    public InexistentOpereFileException() {
        super("OPERE file doesn't exist!");
    }
}

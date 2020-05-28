package exceptions;

public class InexistentTeatreFileException extends RuntimeException{
    public InexistentTeatreFileException() {
        super("TEATRE file doesn't exist!");
    }

}

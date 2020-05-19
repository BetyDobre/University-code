package exceptions;

public class InexistentStandUpsFileException extends RuntimeException {
    public InexistentStandUpsFileException()  {
        super("STAND-UPS file doesn't exist!");
    }
}

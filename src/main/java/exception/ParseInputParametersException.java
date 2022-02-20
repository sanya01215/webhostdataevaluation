package exception;

public class ParseInputParametersException extends RuntimeException{
    public ParseInputParametersException() {
        super("Incorrect input line parameters.");
    }

    public ParseInputParametersException(String message) {
        super(message);
    }
}

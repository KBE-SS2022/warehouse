package warehouse.api.exception;

public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(String message) {
        super(message);
    }
}

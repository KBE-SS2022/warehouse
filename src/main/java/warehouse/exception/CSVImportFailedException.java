package warehouse.exception;

public class CSVImportFailedException extends RuntimeException {

    public CSVImportFailedException(String message) {
        super(message);
    }
}

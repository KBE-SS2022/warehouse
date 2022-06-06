package warehouse.api.exception;

public class UnknownIngredientID extends RuntimeException {

    public UnknownIngredientID(String message) {
        super(message);
    }
}

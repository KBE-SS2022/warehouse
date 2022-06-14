package warehouse.api.exception;

public class PizzaNotFoundException extends Exception {

    public PizzaNotFoundException(String message){
        super(message);
    }
}

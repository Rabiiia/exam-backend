package errorhandling;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

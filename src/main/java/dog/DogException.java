package dog;

/**
 * Represents an exception specific to the DOG application.
 */
public class DogException extends Exception {
    public DogException(String message) {
        super(message);
    }
}
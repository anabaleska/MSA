package backend.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException {
    public InvalidUserCredentialException() {
        super("Invalid user credentials!");
    }
}

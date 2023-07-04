package pi.dev.realestate.exceptions;

public class ParticipantExistsException extends RuntimeException {
    public ParticipantExistsException(String message) {
        super(message);
    }
}

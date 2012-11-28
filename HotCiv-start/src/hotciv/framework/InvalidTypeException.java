package hotciv.framework;

/**
 * Subclass of RuntimeException used for easier debugging when dealing with invalid unittypes at runtime.
 * Added by L&M.
 */
public class InvalidTypeException extends RuntimeException {
    private String message;

    public InvalidTypeException(String type) {
        this.message = "\""+type+"\" was not found as a type in one of the maps in GameConstants.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}

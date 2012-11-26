package hotciv.framework;

/**
 * Subclass of RuntimeException used for easier debugging when dealing with invalid unittypes at runtime.
 * Added by L&M.
 */
public class InvalidUnittypeException extends RuntimeException {
    private String message;

    public InvalidUnittypeException(String unittype) {
        this.message = "\""+unittype+"\" was not found as a unittype in one of the maps in GameConstants.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}

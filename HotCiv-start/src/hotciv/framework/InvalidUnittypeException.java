package hotciv.framework;

/**
 * Subclass of RuntimeException used for easier debugging when dealing with invalid unittypes at runtime.
 * Added by L&M.
 */
public class InvalidUnittypeException extends RuntimeException {
    private String message;

    public InvalidUnittypeException(String message) {
        this.message = message;
    }

    @Override
   public String getMessage() {
       return message;
   }
}

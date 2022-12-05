package bdm.test.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Such account doesn't exist yet");
    }
}

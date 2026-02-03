package se.iths.joakim.bankomatlabb2.exception;

public class MaxWithdrawalExceededException extends RuntimeException {
    public MaxWithdrawalExceededException(String message) {
        super(message);
    }
}

package conta.api.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class Conflict extends Exception {

    private static final long serialVersionUID = 1L;

    public Conflict(final String message) {
        super(NOT_FOUND, message);
    }
}

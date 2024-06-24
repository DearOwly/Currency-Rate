package so.sonya.finservice.service.centralbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CentralBankException extends ResponseStatusException {
    private static final String REASON = "Central Bank Error";

    public CentralBankException(Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, REASON, cause);
    }
}

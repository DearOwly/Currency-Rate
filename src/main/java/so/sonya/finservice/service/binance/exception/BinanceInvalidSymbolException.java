package so.sonya.finservice.service.binance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import so.sonya.finservice.binance.exception.BinanceApiException;
import so.sonya.finservice.domain.Currency;

public class BinanceInvalidSymbolException extends ResponseStatusException {
    private static final String REASON_FORMAT = "Invalid symbol: %s";

    public BinanceInvalidSymbolException(BinanceApiException cause, Currency symbol) {
        super(HttpStatus.BAD_REQUEST, String.format(REASON_FORMAT, symbol), cause);
    }
}

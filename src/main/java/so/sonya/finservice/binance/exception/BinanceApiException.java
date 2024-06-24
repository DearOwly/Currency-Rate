package so.sonya.finservice.binance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BinanceApiException extends RuntimeException{
    private BinanceApiError error;

    public BinanceApiException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMessage();
        }
        return super.getMessage();
    }
}

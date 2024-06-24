package so.sonya.finservice.binance.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BinanceApiError {
    private int code;
    private String message;
}

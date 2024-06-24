package so.sonya.finservice.binance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TickerPrice {
    private String symbol;
    private String price;
}

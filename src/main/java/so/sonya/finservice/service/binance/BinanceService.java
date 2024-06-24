package so.sonya.finservice.service.binance;

import so.sonya.finservice.domain.Currency;

public interface BinanceService {
    Double getLatestRate(Currency symbol);
}

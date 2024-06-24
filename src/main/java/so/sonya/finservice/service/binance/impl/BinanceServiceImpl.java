package so.sonya.finservice.service.binance.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import so.sonya.finservice.binance.client.BinanceApiClientFactory;
import so.sonya.finservice.binance.client.BinanceApiRestClient;
import so.sonya.finservice.binance.domain.TickerStatistics;
import so.sonya.finservice.binance.exception.BinanceApiException;
import so.sonya.finservice.domain.Currency;
import so.sonya.finservice.service.binance.BinanceService;
import so.sonya.finservice.service.binance.exception.BinanceInvalidSymbolException;

@Service
@ConditionalOnProperty(
        prefix = "currency-rate-service.binance",
        value = "mock",
        havingValue = "false"
)
public class BinanceServiceImpl implements BinanceService {
    private final BinanceApiRestClient client;

    public BinanceServiceImpl() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        client = factory.newRestClient();
    }

    @Override
    public Double getLatestRate(Currency symbol) {
        // no USDT/USDT symbol
        if (symbol == Currency.USDT) {
            return 1.0;
        }

        try {
            TickerStatistics statistics = client.get24HrPriceStatistics(getSymbol(symbol));
            return Double.valueOf(statistics.getLastPrice());
        } catch (BinanceApiException e) {
            throw new BinanceInvalidSymbolException(e, symbol);
        }
    }

    private static String getSymbol(Currency symbol) {
        return String.format("%s%s", symbol, Currency.USDT);
    }
}

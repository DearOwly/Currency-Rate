package so.sonya.finservice.binance.client;

import so.sonya.finservice.binance.domain.TickerPrice;
import so.sonya.finservice.binance.domain.TickerStatistics;

import java.util.List;

public interface BinanceApiRestClient {
    TickerStatistics get24HrPriceStatistics(String symbol);
    List<TickerPrice> getAllPrices();
}

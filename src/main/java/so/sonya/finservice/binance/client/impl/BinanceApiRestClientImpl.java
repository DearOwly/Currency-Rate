package so.sonya.finservice.binance.client.impl;

import so.sonya.finservice.binance.client.BinanceApiRestClient;
import so.sonya.finservice.binance.service.BinanceApiService;
import so.sonya.finservice.binance.domain.TickerPrice;
import so.sonya.finservice.binance.domain.TickerStatistics;

import java.util.List;

import static so.sonya.finservice.binance.service.BinanceApiServiceGenerator.executeSync;
import static so.sonya.finservice.binance.service.BinanceApiServiceGenerator.createService;

public class BinanceApiRestClientImpl implements BinanceApiRestClient {

    private final BinanceApiService binanceApiService;

    public BinanceApiRestClientImpl() {
        binanceApiService = createService(BinanceApiService.class);
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return executeSync(binanceApiService.get24HrPriceStatistics(symbol));
    }

    @Override
    public List<TickerPrice> getAllPrices() {
        return executeSync(binanceApiService.getLatestPrices());
    }
}

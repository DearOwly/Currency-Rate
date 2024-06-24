package so.sonya.finservice.binance.client;

import so.sonya.finservice.binance.client.impl.BinanceApiRestClientImpl;

public class BinanceApiClientFactory {

    public BinanceApiRestClient newRestClient() {
        return new BinanceApiRestClientImpl();
    }

    public static BinanceApiClientFactory newInstance() {
        return new BinanceApiClientFactory();
    }
}

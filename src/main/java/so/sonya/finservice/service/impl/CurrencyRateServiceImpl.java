package so.sonya.finservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import so.sonya.finservice.domain.Currency;
import so.sonya.finservice.domain.request.RatesRequest;
import so.sonya.finservice.domain.response.RatesResponse;
import so.sonya.finservice.service.CurrencyRateService;
import so.sonya.finservice.service.binance.BinanceService;
import so.sonya.finservice.service.centralbank.CentralBankService;
import so.sonya.finservice.service.centralbank.response.CentralBankRatesResponse;
import so.sonya.finservice.util.RateCalculator;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private static final Currency DEFAULT_BASE_CURRENCY = Currency.USD;

    private final CentralBankService centralBank;
    private final BinanceService binance;

    @Override
    public RatesResponse getLatestRates(RatesRequest request) {
        Currency base;

        if (request.getBase() == null) {
            base = DEFAULT_BASE_CURRENCY;
        } else {
            base = request.getBase();
        }

        List<Currency> symbols = request.getSymbols();

        if (symbols == null || symbols.isEmpty()) {
            symbols = Arrays.stream(Currency.values())
                    .filter(currency -> !currency.equals(base))
                    .toList();
        }

        CentralBankRatesResponse centralBankResponse = centralBank.getLatestRates();

        Currency centralBankBase = centralBankResponse.getBase();

        RateCalculator rateCalculator = new RateCalculator(centralBankBase);

        centralBankResponse.getRates()
                .forEach((symbol, rate) -> {
                    rateCalculator.add(centralBankBase, symbol, rate);
                });

        symbols.stream()
                .filter(Currency::isCrypto)
                .forEach(symbol -> {
                    rateCalculator.add(symbol, Currency.USDT, binance.getLatestRate(symbol));
                });

        if (base.isCrypto()) {
            rateCalculator.add(base, Currency.USDT, binance.getLatestRate(base));
        }

        Map<Currency, Double> rates = symbols.stream()
                .collect(Collectors.toMap(symbol -> symbol, symbol -> rateCalculator.getRate(base, symbol)));

        return RatesResponse.builder()
                .date(new Date())
                .timestamp(Instant.now().getEpochSecond())
                .base(base)
                .rates(rates)
                .build();
    }
}

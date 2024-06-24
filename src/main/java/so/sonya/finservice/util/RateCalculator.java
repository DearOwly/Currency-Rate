package so.sonya.finservice.util;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import so.sonya.finservice.domain.Currency;

import java.util.HashMap;
import java.util.Map;

public final class RateCalculator {
    private final Currency centralBankBase;
    private final Map<CurrencyPair, Double> rates = new HashMap<>();

    public RateCalculator(Currency centralBankBase) {
        this.centralBankBase = centralBankBase;
        add(Currency.USD, Currency.USDT, 1.0);
    }

    public Double getRate(Currency from, Currency to) {
        CurrencyPair currencyPair = CurrencyPair.of(from, to);

        if (rates.containsKey(currencyPair)) {
            return rates.get(currencyPair);
        }

        if (!from.isCrypto() && !to.isCrypto()) {
            return getRate(from, centralBankBase) * getRate(centralBankBase, to);
        }

        if (from.isCrypto() && !to.isCrypto()) {
            // assume USDT = USD (not actually true, but hard to get the exact rate)
            return getRate(from, Currency.USDT) * getRate(Currency.USD, to);
        }

        // `to` is always crypto by now
        if (!from.isCrypto()) {
            // assume USDT = USD (not actually true, but hard to get the exact rate)
            return getRate(from, Currency.USD) * getRate(Currency.USDT, to);
        }

        // both `from` and `to` are crypto
        return getRate(from, Currency.USDT) * getRate(Currency.USDT, to);
    }

    public void add(Currency from, Currency to, Double rate) {
        rates.put(CurrencyPair.of(from, from), 1.0);
        rates.put(CurrencyPair.of(to, to), 1.0);
        rates.put(CurrencyPair.of(from, to), rate);
        rates.put(CurrencyPair.of(to, from), 1.0 / rate);
    }

    @RequiredArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    private static final class CurrencyPair {
        private final Currency from;
        private final Currency to;
    }
}

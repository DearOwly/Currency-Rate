package so.sonya.finservice.service.centralbank.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import so.sonya.finservice.domain.Currency;
import so.sonya.finservice.service.centralbank.response.CentralBankRatesResponse;
import so.sonya.finservice.service.centralbank.CentralBankService;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(
        prefix = "currency-rate-service.central-bank",
        value = "mock",
        havingValue = "true"
)
public class CentralBankServiceMockImpl implements CentralBankService {
    private static final String DISCLAIMER = "https://www.cbr-xml-daily.ru/#terms";
    private static final Currency BASE = Currency.RUB;

    @Override
    public CentralBankRatesResponse getLatestRates() {
        Map<Currency, Double> rates = Arrays.stream(Currency.values())
                .filter(currency -> !currency.equals(BASE))
                .collect(Collectors.toMap(currency -> currency, currency -> 0.0));

        return CentralBankRatesResponse.builder()
                .disclaimer(DISCLAIMER)
                .date(new Date())
                .timestamp(Instant.now().getEpochSecond())
                .rates(rates)
                .build();
    }
}

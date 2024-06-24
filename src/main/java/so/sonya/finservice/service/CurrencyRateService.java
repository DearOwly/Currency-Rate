package so.sonya.finservice.service;

import so.sonya.finservice.domain.request.RatesRequest;
import so.sonya.finservice.domain.response.RatesResponse;

public interface CurrencyRateService {
    RatesResponse getLatestRates(RatesRequest request);
}

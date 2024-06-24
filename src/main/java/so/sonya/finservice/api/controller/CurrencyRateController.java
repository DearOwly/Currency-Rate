package so.sonya.finservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import so.sonya.finservice.api.CurrencyRateApi;
import so.sonya.finservice.domain.request.RatesRequest;
import so.sonya.finservice.domain.response.RatesResponse;
import so.sonya.finservice.service.CurrencyRateService;

@RestController
@RequiredArgsConstructor
public class CurrencyRateController implements CurrencyRateApi {
    private final CurrencyRateService service;

    @Override
    public RatesResponse getLatestRates(RatesRequest request) {
        return service.getLatestRates(request);
    }
}

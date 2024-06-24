package so.sonya.finservice.service.centralbank.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import so.sonya.finservice.service.centralbank.exception.CentralBankException;
import so.sonya.finservice.service.centralbank.response.CentralBankRatesResponse;
import so.sonya.finservice.service.centralbank.CentralBankService;
import so.sonya.finservice.service.centralbank.util.JsonBodyHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Service
@ConditionalOnProperty(
        prefix = "currency-rate-service.central-bank",
        value = "mock",
        havingValue = "false"
)
public class CentralBankServiceImpl implements CentralBankService {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private static final String REQUEST_URI = "https://www.cbr-xml-daily.ru/latest.js";

    private static final HttpRequest REQUEST = HttpRequest
            .newBuilder(URI.create(REQUEST_URI))
            .header("Accept", "application/json")
            .build();

    private static final JsonBodyHandler<CentralBankRatesResponse> JSON_BODY_HANDLER = new JsonBodyHandler<>(CentralBankRatesResponse.class);

    @Override
    public CentralBankRatesResponse getLatestRates() {
        try {
            return HTTP_CLIENT.send(REQUEST, JSON_BODY_HANDLER).body().get();
        } catch (Exception e) {
            throw new CentralBankException(e);
        }
    }
}

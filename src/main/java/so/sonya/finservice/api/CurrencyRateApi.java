package so.sonya.finservice.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import so.sonya.finservice.domain.request.RatesRequest;
import so.sonya.finservice.domain.response.RatesResponse;

@RequestMapping("/api")
public interface CurrencyRateApi {
    @ApiOperation(value = "get latest rates of currencies", httpMethod = "GET", produces = "RatesResponse")
    @GetMapping("/latest")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully get the rates of currencies"),
            @ApiResponse(code = 400, message = "invalid request content")
    })
    RatesResponse getLatestRates(RatesRequest request);
}

package so.sonya.finservice.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import so.sonya.finservice.domain.Currency;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Запрос курсов валют")
public class RatesRequest {
    @Schema(description = "Исходная валюта", example = "RUB")
    private Currency base;

    @Schema(description = "Список целевых валют", example = "USD, BTC, EUR")
    private List<Currency> symbols;
}

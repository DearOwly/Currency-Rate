package so.sonya.finservice.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import so.sonya.finservice.domain.Currency;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Ответ с курсами валют")
public class RatesResponse {
    @Schema(description = "Дата", example = "2024-12-31")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Schema(description = "Время в формате UNIX-time", example = "1717623386")
    private Long timestamp;

    @Schema(description = "Исходная валюта", example = "RUB")
    private Currency base;

    @Schema(description = "Курсы исходной валюты к целевым")
    private Map<Currency, Double> rates;
}

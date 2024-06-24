package so.sonya.finservice.service.centralbank.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import so.sonya.finservice.domain.Currency;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CentralBankRatesResponse {
    private String disclaimer;
    private Date date;
    private Long timestamp;
    private Currency base;
    private Map<Currency, Double> rates;
}

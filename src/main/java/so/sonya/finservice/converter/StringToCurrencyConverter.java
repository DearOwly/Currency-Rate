package so.sonya.finservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import so.sonya.finservice.domain.Currency;

@Component
public class StringToCurrencyConverter implements Converter<String, Currency> {
    @Override
    public Currency convert(String source) {
        return Currency.valueOf(source.toUpperCase());
    }
}

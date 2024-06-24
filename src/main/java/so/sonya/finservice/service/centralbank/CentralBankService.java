package so.sonya.finservice.service.centralbank;

import so.sonya.finservice.service.centralbank.response.CentralBankRatesResponse;

public interface CentralBankService {
    CentralBankRatesResponse getLatestRates();
}

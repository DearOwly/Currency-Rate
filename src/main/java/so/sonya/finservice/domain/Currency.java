package so.sonya.finservice.domain;

import lombok.Getter;

@Getter
public enum Currency {
    AED(false),
    AMD(false),
    AUD(false),
    AZN(false),
    BGN(false),
    BRL(false),
    BYN(false),
    CAD(false),
    CHF(false),
    CNY(false),
    CZK(false),
    DKK(false),
    EGP(false),
    EUR(false),
    GBP(false),
    GEL(false),
    HKD(false),
    HUF(false),
    IDR(false),
    INR(false),
    JPY(false),
    KGS(false),
    KRW(false),
    KZT(false),
    MDL(false),
    NOK(false),
    NZD(false),
    PLN(false),
    QAR(false),
    RON(false),
    RSD(false),
    RUB(false),
    SEK(false),
    SGD(false),
    THB(false),
    TJS(false),
    TMT(false),
    TRY(false),
    UAH(false),
    USD(false),
    UZS(false),
    VND(false),
    XDR(false),
    ZAR(false),

    BTC(true),
    ETH(true),
    BNB(true),
    XRP(true),
    USDT(true);

    private final boolean isCrypto;

    Currency(boolean isCrypto) {
        this.isCrypto = isCrypto;
    }
}

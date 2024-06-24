package so.sonya.finservice.binance.service;
import so.sonya.finservice.binance.domain.TickerPrice;
import so.sonya.finservice.binance.domain.TickerStatistics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface BinanceApiService {
    @GET("/api/v1/ticker/24hr")
    Call<TickerStatistics> get24HrPriceStatistics(@Query("symbol") String symbol);

    @GET("/api/v1/ticker/allPrices")
    Call<List<TickerPrice>> getLatestPrices();
}

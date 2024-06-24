package so.sonya.finservice.binance.service;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import so.sonya.finservice.binance.exception.BinanceApiError;
import so.sonya.finservice.binance.exception.BinanceApiException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static so.sonya.finservice.binance.constant.BinanceApiConstants.API_BASE_URL;

public class BinanceApiServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                BinanceApiError apiError = getBinanceApiError(response);
                throw new BinanceApiException(apiError);
            }
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }

    public static BinanceApiError getBinanceApiError(Response<?> response) throws IOException, BinanceApiException {
        return (BinanceApiError)retrofit.responseBodyConverter(BinanceApiError.class, new Annotation[0])
                .convert(response.errorBody());
    }
}

package so.sonya.finservice.service.centralbank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<Supplier<T>> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Class<T> targetClass;

    @Override
    public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJSON(this.targetClass);
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType));
    }

    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try (InputStream stream = inputStream) {
                return OBJECT_MAPPER.readValue(stream, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}

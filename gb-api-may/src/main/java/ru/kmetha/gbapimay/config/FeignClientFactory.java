package ru.kmetha.gbapimay.config;

import feign.*;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.kmetha.gbapimay.category.api.CategoryGateway;
import ru.kmetha.gbapimay.product.api.ProductGateway;

import java.util.concurrent.TimeUnit;

import static feign.FeignException.errorStatus;

@Component
@RequiredArgsConstructor
public class FeignClientFactory {

    private final ObjectFactory<HttpMessageConverters> messageConverters;
    private final ObjectProvider<HttpMessageConverterCustomizer> customizers;

    @Autowired
    private final GbApiProperties gbApiProperties;

    public <T> T newFeignGateway(Class<T> requiredType, String url) {
        return Feign.builder()
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters, this.customizers))))
                .retryer(new Retryer.Default(
                        gbApiProperties.getConnection().getPeriod(),
                        gbApiProperties.getConnection().getMaxPeriod(),
                        gbApiProperties.getConnection().getMaxAttempts()
                ))
                .errorDecoder(errorDecoder())
                .options(new Request.Options(
                        gbApiProperties.getConnection().getConnectTimeout(),
                        TimeUnit.MILLISECONDS,
//                        gbApiProperties.getConnection().getReadTimeoutUnit(),
                        gbApiProperties.getConnection().getReadTimeout(),
                        TimeUnit.MILLISECONDS,
//                        gbApiProperties.getConnection().getReadTimeoutUnit(),
                        gbApiProperties.getConnection().isFollowRedirect()
                ))
//                .client()
                .contract(new SpringMvcContract())
                .logLevel(Logger.Level.FULL)
                .logger(new Slf4jLogger(requiredType))
                .target(requiredType, url);
    }

    private ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            FeignException exception = errorStatus(methodKey, response);
            if (exception.status() == 500 || exception.status() == 503) {
                return new RetryableException(
                        response.status(),
                        exception.getMessage(),
                        response.request().httpMethod(),
                        exception,
                        null,
                        response.request());
            }
            return exception;
        };
    }
}

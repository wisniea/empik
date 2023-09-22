package pl.wisniea.empik.util.configurations;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wisniea.empik.util.exceptions.FeignErrorDecoder;

@EnableFeignClients(basePackages = "pl.wisniea.empik.communication.client")
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}

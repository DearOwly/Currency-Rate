package so.sonya.finservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitProperties {
    private String queueName;
    private Scheduled scheduled;

    @Getter
    @Setter
    public static class Scheduled {
        private String queueName;
        private String cron;
    }
}

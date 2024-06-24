package so.sonya.finservice.rabbit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import so.sonya.finservice.properties.RabbitProperties;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class RabbitConfig {
    private final RabbitProperties rabbitProperties;

    @Bean
    public Queue regularQueue() {
        return new Queue(rabbitProperties.getQueueName());
    }

    @Bean
    public Queue scheduledQueue() {
        return new Queue(rabbitProperties.getScheduled().getQueueName());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

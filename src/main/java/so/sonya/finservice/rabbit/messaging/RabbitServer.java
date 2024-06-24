package so.sonya.finservice.rabbit.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import so.sonya.finservice.domain.request.RatesRequest;
import so.sonya.finservice.domain.response.RatesResponse;
import so.sonya.finservice.properties.RabbitProperties;
import so.sonya.finservice.service.CurrencyRateService;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitServer {
    private final CurrencyRateService service;

    private final RabbitProperties properties;
    private final RabbitTemplate rabbit;

    @RabbitListener(queues = { "${rabbitmq.queue-name}" })
    public RatesResponse getLatestRates(RatesRequest request) {
        return service.getLatestRates(request);
    }

    @Scheduled(cron = "${rabbitmq.scheduled.cron: * * * * * *}")
    public void sendDailyRates() {
        RatesRequest request = new RatesRequest();
        RatesResponse response = service.getLatestRates(request);
        log.info("Sending daily rates to RabbitMQ: {}", response);
        rabbit.convertAndSend(properties.getScheduled().getQueueName(), response);
    }
}

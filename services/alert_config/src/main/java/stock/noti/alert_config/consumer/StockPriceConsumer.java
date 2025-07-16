package stock.noti.alert_config.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.noti.alert_config.dto.StockPriceEvent;
import stock.noti.alert_config.service.AlertEvaluatorService;

@Slf4j
@Component
public class StockPriceConsumer {
    private final ObjectMapper objectMapper;
    private final AlertEvaluatorService evaluatorService;

    public StockPriceConsumer(AlertEvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
        this.objectMapper = new ObjectMapper();
    }


    @KafkaListener(topics = "price_update", groupId = "alert-config-group")
    public void consume(String message) {
        try {
            StockPriceEvent event = objectMapper.readValue(message, StockPriceEvent.class);
            log.info("Nhận dữ liệu: {}", event);
            evaluatorService.evaluate(event);
        } catch (Exception e) {
            log.error("Lỗi khi xử lý message kafka: {}", message, e);
        }
    }
}

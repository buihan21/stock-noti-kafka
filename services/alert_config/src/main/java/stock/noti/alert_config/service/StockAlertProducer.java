package stock.noti.alert_config.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import stock.noti.alert_config.dto.StockAlertEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockAlertProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendAlert(StockAlertEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("stock-alert", message);
            log.info("Gửi cảnh báo kafka: {}", message);
        } catch (JsonProcessingException e) {
            log.error("Gửi cảnh báo thất bại",e);
        }
    }
}

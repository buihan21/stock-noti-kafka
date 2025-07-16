package stock.noti.notification_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import stock.noti.notification_service.dto.StockAlertEvent;

@Slf4j
@Component
public class StockAlertConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "stock-alert", groupId = "notification-group")
    public void listen(String message) {
        try {
            StockAlertEvent event = objectMapper.readValue(message, StockAlertEvent.class);
            log.info("[NOTIFICATION] Gửi thông báo cho user {}: {} {} {} (Giá hiện tại: {})",
                    event.getUserId(),
                    event.getSymbol(),
                    event.getCondition(),
                    event.getTargetPrice(),
                    event.getCurrentPrice());
        } catch (Exception e) {
            log.error("Lỗi khi xử lý alert message: {}", message, e);
        }
    }
}

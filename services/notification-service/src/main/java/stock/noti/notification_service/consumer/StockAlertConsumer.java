package stock.noti.notification_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import stock.noti.notification_service.dto.StockAlertEvent;
import stock.noti.notification_service.model.AlertLog;
import stock.noti.notification_service.repository.AlertLogRepository;
import stock.noti.notification_service.service.EmailService;

import java.time.Instant;

@Slf4j
@Component
public class StockAlertConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AlertLogRepository alertLogRepository;
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "stock-alert", groupId = "notification-group")
    public void listen(String message) {
        log.info("Received stock alert message: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(message);
            AlertLog alertLog = new AlertLog();
            alertLog.setSymbol(json.get("symbol").asText());
            alertLog.setCurrentPrice(json.get("currentPrice").asDouble());
            alertLog.setTargetPrice(json.get("targetPrice").asDouble());
            alertLog.setCondition(json.get("condition").asText());
            alertLog.setTimestamp(Instant.now());
            alertLogRepository.save(alertLog);

            log.info("Alert saved to MongoDB!");

//            StockAlertEvent event = objectMapper.readValue(message, StockAlertEvent.class);
//            log.info("[NOTIFICATION] Gửi thông báo cho user {}: {} {} {} (Giá hiện tại: {})",
//                    event.getUserId(),
//                    event.getSymbol(),
//                    event.getCondition(),
//                    event.getTargetPrice(),
//                    event.getCurrentPrice());
            // Gửi mail
            StockAlertEvent alert = new StockAlertEvent();
            alert.setSymbol(json.get("symbol").asText());
            alert.setCurrentPrice(json.get("currentPrice").asDouble());
            alert.setTargetPrice(json.get("targetPrice").asDouble());
            alert.setCondition(json.get("condition").asText());
            alert.setEmail(json.get("email").asText());
            alert.setAlertTime(json.get("alertTime").asText());
            emailService.sendAlertEmail(alert);
            log.info("Send alert email successfully to " + alert.getEmail() );
        } catch (Exception e) {
            log.error("Failed to parse or save alert", e);
        }
    }
}

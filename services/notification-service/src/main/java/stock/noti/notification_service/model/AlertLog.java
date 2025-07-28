package stock.noti.notification_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "alerts")
public class AlertLog {
    @Id
    private String id;
    private String symbol;
    private double currentPrice;
    private double targetPrice;
    private String condition; //GREATER_THAN, LESS_THAN
    private Instant timestamp;
}

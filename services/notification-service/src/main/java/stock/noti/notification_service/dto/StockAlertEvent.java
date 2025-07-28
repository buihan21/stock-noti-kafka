package stock.noti.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockAlertEvent {
    private String userId;
    private String symbol;
    private double currentPrice;
    private double targetPrice;
    private String condition;
    private String email;
    private String alertTime;
}

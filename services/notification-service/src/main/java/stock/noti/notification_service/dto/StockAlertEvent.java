package stock.noti.notification_service.dto;

import lombok.Data;

@Data
public class StockAlertEvent {
    private String userId;
    private String symbol;
    private double currentPrice;
    private double targetPrice;
    private String condition;
    private String alertTime;
}

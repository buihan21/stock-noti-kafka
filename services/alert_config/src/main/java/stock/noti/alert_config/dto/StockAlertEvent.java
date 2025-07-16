package stock.noti.alert_config.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockAlertEvent {
    private String userId;
    private String symbol;
    private double currentPrice;
    private double targetPrice;
    private String condition;
    private String alertTime;
}

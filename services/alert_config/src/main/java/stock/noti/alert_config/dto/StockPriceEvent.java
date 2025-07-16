package stock.noti.alert_config.dto;

import lombok.Data;

@Data
public class StockPriceEvent {
    private String symbol;
    private double price;
    private String timestamp;
}

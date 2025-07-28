package stock.noti.alert_config.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAlertRequest {
    //private String userId;
    private String symbol;
    private BigDecimal targetPrice;
    private String condition;
}

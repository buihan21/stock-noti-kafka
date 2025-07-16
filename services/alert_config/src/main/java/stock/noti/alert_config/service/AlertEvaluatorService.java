package stock.noti.alert_config.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.noti.alert_config.dto.StockAlertEvent;
import stock.noti.alert_config.dto.StockPriceEvent;
import stock.noti.alert_config.entity.AlertConfig;
import stock.noti.alert_config.repository.AlertConfigRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AlertEvaluatorService  {
    private final AlertConfigRepository alertConfigRepository;
    private final StockAlertProducer alertProducer;

    public void evaluate(StockPriceEvent stockPriceEvent) {
        List<AlertConfig> alertConfigs = alertConfigRepository.findAll();
        for (AlertConfig alertConfig : alertConfigs) {
            if (alertConfig.getSymbol().equalsIgnoreCase(stockPriceEvent.getSymbol())) {
                continue;
            }
            boolean matched = switch (alertConfig.getCondition()) {
                case "GREATER_THAN" -> stockPriceEvent.getPrice() > alertConfig.getTargetPrice().doubleValue();
                case "LESS_THAN" -> stockPriceEvent.getPrice() < alertConfig.getTargetPrice().doubleValue();
                default -> false;
            };
            if (matched) {
                log.info("Cảnh báo: [{}] thỏa mãn điều kiện [{} {} {}]",
                        alertConfig.getUserId(),
                        alertConfig.getSymbol(),
                        alertConfig.getCondition(),
                        alertConfig.getTargetPrice());
            }

            StockAlertEvent alertEvent = StockAlertEvent.builder()
                    .userId(alertConfig.getUserId())
                    .symbol(alertConfig.getSymbol())
                    .currentPrice(stockPriceEvent.getPrice())
                    .targetPrice(alertConfig.getTargetPrice().doubleValue())
                    .condition(alertConfig.getCondition())
                    .alertTime(stockPriceEvent.getTimestamp())
                    .build();
            alertProducer.sendAlert(alertEvent);
        }
    }
}

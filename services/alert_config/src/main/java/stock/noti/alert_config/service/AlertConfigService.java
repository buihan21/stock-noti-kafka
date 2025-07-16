package stock.noti.alert_config.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock.noti.alert_config.entity.AlertConfig;
import stock.noti.alert_config.repository.AlertConfigRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlertConfigService {
    private final AlertConfigRepository alertConfigRepository;
    public AlertConfig save(AlertConfig alertConfig) {
        return alertConfigRepository.save(alertConfig);
    }

    public List<AlertConfig> findAll() {
        return alertConfigRepository.findAll();
    }

    public void deleteById(UUID id) {alertConfigRepository.deleteById(id);}
}

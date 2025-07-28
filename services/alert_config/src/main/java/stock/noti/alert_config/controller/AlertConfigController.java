package stock.noti.alert_config.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.noti.alert_config.dto.CreateAlertRequest;
import stock.noti.alert_config.entity.AlertConfig;
import stock.noti.alert_config.service.AlertConfigService;
import stock.noti.alert_config.service.UserClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertConfigController {
    private final AlertConfigService alertService;
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<AlertConfig> createAlert(@Valid @RequestBody CreateAlertRequest createAlertRequest,
                                                   @RequestHeader("Authorization") String token) {
        Long userId = userClient.getUserIdFromToken(token);
        AlertConfig alertConfig = new AlertConfig();
        alertConfig.setUserId(userId.toString());
        alertConfig.setSymbol(createAlertRequest.getSymbol());
        alertConfig.setTargetPrice(createAlertRequest.getTargetPrice());
        alertConfig.setCondition(createAlertRequest.getCondition());
        alertConfig.setEmail(userClient.getEmailByUserId(userId));
        return ResponseEntity.ok(alertService.save(alertConfig));
    }

    @GetMapping
    public ResponseEntity<List<AlertConfig>> getAll() {
        return ResponseEntity.ok( alertService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable UUID id) {
        alertService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

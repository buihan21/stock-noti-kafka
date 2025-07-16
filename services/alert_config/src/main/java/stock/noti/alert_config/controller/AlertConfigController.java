package stock.noti.alert_config.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.noti.alert_config.entity.AlertConfig;
import stock.noti.alert_config.service.AlertConfigService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertConfigController {
    private final AlertConfigService alertService;
    @PostMapping
    public ResponseEntity<AlertConfig> createAlert(@Valid @RequestBody AlertConfig alertConfig) {
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

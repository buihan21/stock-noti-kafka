package stock.noti.alert_config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.noti.alert_config.entity.AlertConfig;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertConfigRepository extends JpaRepository<AlertConfig, UUID> {
}

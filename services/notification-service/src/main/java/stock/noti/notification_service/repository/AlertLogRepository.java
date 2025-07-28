package stock.noti.notification_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import stock.noti.notification_service.model.AlertLog;

public interface AlertLogRepository extends MongoRepository<AlertLog,String> {
}

package stock.noti.alert_config.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alert_configs")
public class AlertConfig {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(nullable = false)
    private BigDecimal targetPrice;

    @Column(nullable = false)
    @Pattern(regexp = "GREATER_THAN|LESS_THAN")
    private String condition; // GREATER_THAN, LESS_THAN

    @Column(name = "email")
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

//    @Enumerated(EnumType.STRING)
//    private Direction direction;
//
//    public enum Direction {
//        GREATER_THAN,
//        LESS_THAN
//    }
}

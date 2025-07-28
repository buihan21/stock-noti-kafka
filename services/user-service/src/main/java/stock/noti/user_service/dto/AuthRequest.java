package stock.noti.user_service.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

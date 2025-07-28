package stock.noti.alert_config.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock.noti.alert_config.dto.UserDTO;

@Service
public class UserClient {
    @Value("${user-service.url}")
    private String userServiceUrl;
    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Long getUserIdFromToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
            userServiceUrl + "/api/auth/me",
                HttpMethod.GET,
                entity, UserDTO.class
        );
        return response.getBody().getId();
    }

    public String getEmailByUserId(Long userId) {
        return restTemplate.getForObject(
                userServiceUrl + "/api/auth/email?userId=" + userId,
                String.class
        );
    }
}

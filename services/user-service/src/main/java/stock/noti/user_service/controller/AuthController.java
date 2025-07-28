package stock.noti.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.noti.user_service.dto.AuthResponse;
import stock.noti.user_service.dto.LoginRequest;
import stock.noti.user_service.dto.RegisterRequest;
import stock.noti.user_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = authService.register(request);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

package stock.noti.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.noti.user_service.model.User;
import stock.noti.user_service.service.JwtService;
import stock.noti.user_service.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtService.extractUsername(jwt);
        User user = userService.findByUsername(username).orElse(null);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public String getEmailByUserId(@RequestParam("userId") Long userId) {
        return userService.findEmailByUserId(userId);
    }
}

package stock.noti.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stock.noti.user_service.dto.AuthRequest;
import stock.noti.user_service.dto.AuthResponse;
import stock.noti.user_service.dto.LoginRequest;
import stock.noti.user_service.dto.RegisterRequest;
import stock.noti.user_service.model.User;
import stock.noti.user_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String register(RegisterRequest request) {
        // ktra user có tồn tại ko
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists";
            //throw new RuntimeException("Username already exists");
        }
        // Mã hóa password + lưu ttin user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);

        // tạo token cho new user
        return jwtService.generateToken(request.getUsername());

    }

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(
                request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        System.out.println(">>> Before authenticate");
//        authenticationManager
//                .authenticate(
//                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        System.out.println(">>> After authenticate");
        // tạo token
        return jwtService.generateToken(request.getUsername());
    }
}

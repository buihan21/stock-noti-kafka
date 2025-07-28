package stock.noti.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock.noti.user_service.model.User;
import stock.noti.user_service.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String findEmailByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getEmail).orElse(null);
    }
}

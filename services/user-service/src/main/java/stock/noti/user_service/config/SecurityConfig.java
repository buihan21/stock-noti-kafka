package stock.noti.user_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration  authenticationConfiguration) {
//        try {
//            return authenticationConfiguration.getAuthenticationManager();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

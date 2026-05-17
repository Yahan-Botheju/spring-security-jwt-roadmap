package lk.spring_security.stateless_jwt.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    //inject user repo
    private final UserRepository userRepository;

    //find user in db using Spring security
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.userFindByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email" + username));
    }

    //hashed password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

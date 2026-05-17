package lk.spring_security.stateless_jwt.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
}

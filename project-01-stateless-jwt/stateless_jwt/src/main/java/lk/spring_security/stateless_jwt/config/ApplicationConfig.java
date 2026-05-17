package lk.spring_security.stateless_jwt.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    //inject user repo
    private final UserRepository userRepository;
}

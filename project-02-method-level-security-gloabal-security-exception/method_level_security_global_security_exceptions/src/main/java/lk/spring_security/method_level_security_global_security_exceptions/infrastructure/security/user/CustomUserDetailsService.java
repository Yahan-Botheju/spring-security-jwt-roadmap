package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo (CONSTRUCTOR INJECTION)
    private final UserRepository userRepository;

    //(CONSTRUCTOR INJECTION)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo
    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }
}

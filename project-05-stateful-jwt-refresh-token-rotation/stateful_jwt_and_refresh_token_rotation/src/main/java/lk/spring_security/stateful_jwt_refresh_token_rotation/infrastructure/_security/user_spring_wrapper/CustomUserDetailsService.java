package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

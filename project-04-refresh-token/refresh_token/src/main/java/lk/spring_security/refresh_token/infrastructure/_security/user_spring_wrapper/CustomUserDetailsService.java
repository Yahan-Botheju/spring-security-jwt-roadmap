package lk.spring_security.refresh_token.infrastructure._security.user_spring_wrapper;

import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    //inject required dependencies
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

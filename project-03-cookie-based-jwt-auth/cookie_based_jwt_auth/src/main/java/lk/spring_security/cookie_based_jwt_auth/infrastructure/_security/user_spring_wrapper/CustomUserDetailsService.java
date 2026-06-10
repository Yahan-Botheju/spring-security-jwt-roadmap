package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo
    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    //find user by email from db
    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {
        return null;
    }
}

package lk.spring_security.stateless_jwt.infrastructure.security;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo
    private final UserRepository userRepository;

    //implement constructor for user repo injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepository.userFindByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new CustomUserDetails(user);
    }
}

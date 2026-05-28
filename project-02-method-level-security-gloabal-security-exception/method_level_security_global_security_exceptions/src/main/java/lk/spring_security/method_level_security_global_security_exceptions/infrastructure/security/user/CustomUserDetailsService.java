package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    //inject user repo (CONSTRUCTOR INJECTION)
    private final UserRepository userRepository;

    //(CONSTRUCTOR INJECTION)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //check user through custom method and then set to custom user details
    @Override
    public UserDetails loadUserByUsername(
            @NotNull String username
    )throws UsernameNotFoundException {
        User user = userRepository.userFindByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + " , " +username));

        return new CustomUserDetails(user);
    }
}

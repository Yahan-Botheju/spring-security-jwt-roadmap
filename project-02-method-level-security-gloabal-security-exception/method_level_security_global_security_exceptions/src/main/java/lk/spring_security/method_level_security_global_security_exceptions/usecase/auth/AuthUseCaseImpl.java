package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase {

    //inject required classes and spring classes via constructor injection
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtImpl jwtImpl;
    private final AuthenticationManager authenticationManager;

    public AuthUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtImpl jwtImpl, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtImpl = jwtImpl;
        this.authenticationManager = authenticationManager;
    }
}

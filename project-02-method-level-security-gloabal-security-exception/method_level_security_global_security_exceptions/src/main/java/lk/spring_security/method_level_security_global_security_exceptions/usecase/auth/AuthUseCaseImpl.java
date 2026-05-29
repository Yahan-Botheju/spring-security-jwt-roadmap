package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import jakarta.transaction.Transactional;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Role;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    //register user
    @Override
    @Transactional
    public AuthResponseDTO registerUser(
            AuthRequestDTO authRequestDTO
    ){
        //check email availability
        if(userRepository.userFindByEmail(authRequestDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("email already exists");
        }

        //create domain model
        User user = User.builder()
                .email(authRequestDTO.getEmail())
                .password(passwordEncoder.encode(authRequestDTO.getPassword()))
                .role(Role.USER)
                .build();
    }
}

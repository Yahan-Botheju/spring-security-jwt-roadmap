package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.security.JwtService;
import lk.spring_security.stateless_jwt.web.auth.AuthRequest;
import lk.spring_security.stateless_jwt.web.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase{

    //inject required classes and spring classes
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //register new user
    @Override
    @Transactional
    public AuthResponse register(AuthRequest authRequest){

        //check user by email
        if(userRepository.userFindByEmail(authRequest.getEmail()).isPresent()){
            throw new UsernameNotFoundException("Email already exists");
        }

        //create domain model
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .build();

        //save in db
        userRepository.saveUser(user);


    }
}

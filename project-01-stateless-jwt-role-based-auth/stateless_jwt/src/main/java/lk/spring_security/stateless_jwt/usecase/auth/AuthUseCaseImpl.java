package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.security.CustomUserDetails;
import lk.spring_security.stateless_jwt.infrastructure.security.JwtImpl;
import lk.spring_security.stateless_jwt.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.stateless_jwt.web.auth.DTOs.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required classes and spring classes
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

    //register new user
    @Override
    @Transactional
    public AuthResponseDTO register(AuthRequestDTO authRequestDTO){

        //check user by email
        if(userRepository.userFindByEmail(authRequestDTO.getEmail()).isPresent()){
            throw new UsernameNotFoundException("Email already exists");
        }

        //create domain model
        User user = User.builder()
                .email(authRequestDTO.getEmail())
                .password(passwordEncoder.encode(authRequestDTO.getPassword()))
                .role(Role.USER)
                .build();

        //save in db
        userRepository.saveUser(user);

        //generate JWT
        String jwtToken = jwtImpl.generateToken(new CustomUserDetails(user));
        return new AuthResponseDTO(jwtToken);
    }

    //auth response
    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO){
        //check email and password through spring sec
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getPassword()
                )
        );

        //find user in db
        User user = userRepository.userFindByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

        //generate JWT
        String jwtToken = jwtImpl.generateToken(new CustomUserDetails(user));
        return new AuthResponseDTO(jwtToken);
    }
}

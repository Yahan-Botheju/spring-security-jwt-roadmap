package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import jakarta.transaction.Transactional;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Role;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase {

    //inject required classes and spring classes via constructor injection
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    //register user
    @Override
    @Transactional
    public String registerUser(User user ){
        //check email availability
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("email already exists");
        }

        //create domain model
        User createDomainModel = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .build();

        //save user in db
        userRepository.saveUser(user);

        //generate toke
        String jwtToken = jwtService.generateToken(createDomainModel);

        return jwtToken;
    }

    //login user
    @Override
    public String loginUser(String  email, String password){
        //check username and password using spring security
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        //check and find user in db
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

        //generate token
        String jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }
}

package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import jakarta.transaction.Transactional;
import lk.spring_security.cookie_based_jwt_auth.domain.models.Role;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required classes
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;
    private final AuthenticationManager authenticationManager;


    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
        this.authenticationManager = authenticationManager;
    }

    //register user
    @Override
    @Transactional
    public String registerUser(User user){
        //check user already exist in db
        if(userRepository.userFindByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with email already exists");
        }

        //create user domain model
        User createDomainModel = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .build();

        //save user through domain user repo
        userRepository.registerUser(createDomainModel);

        //generate token
        return cookieService.generateToken(createDomainModel);
    }

    //login user
    @Override
    public String loginUser(String email, String password){
        //check given username via CustomUserDetailsService and password check via Auth Provider
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    }
}

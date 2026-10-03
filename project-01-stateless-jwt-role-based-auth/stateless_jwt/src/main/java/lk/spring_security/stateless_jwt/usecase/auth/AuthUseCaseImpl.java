package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.domain.repositories.JwtService;
import lk.spring_security.stateless_jwt.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginResult;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AuthUseCaseImpl implements AuthUseCase{

    //inject required classes and spring classes
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private IdentityManger identityManger;

    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            IdentityManger identityManger
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.identityManger = identityManger;
    }

    //register new user
    @Override
    public RegisterResult register(RegisterCommand registerCommand) {

        //check incoming fields
        if(registerCommand.password().isBlank() || registerCommand.email().isBlank()){
            throw new IllegalStateException("Required fields cannot be empty");
        }

        //create new user through domain
        User newUser = User.createNewUser(
                registerCommand.email(),
                passwordEncoder.encode(registerCommand.password()),
                Role.USER
        );

        User savedUser = userRepository.saveUser(newUser);
        //create token
        String token = jwtService.generateToken(new CustomUserDetails(newUser));

        return new RegisterResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().toString(),
                token
        );
    }

    //login user
    @Override
    public LoginResult login(LoginCommand loginCommand) {
        //check incoming fields
        if(loginCommand.password().isBlank() || loginCommand.email().isBlank()){
            throw new IllegalStateException("Required fields cannot be empty");
        }
        //authenticate user
        identityManger.authenticateUser(loginCommand.email(), loginCommand.password());

        //find user
        User user = userRepository.userFindByEmail(loginCommand.email())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

        String token = jwtService.generateToken(new CustomUserDetails(user));

        return new LoginResult(
                user.getEmail(),
                token
        );
    }

}
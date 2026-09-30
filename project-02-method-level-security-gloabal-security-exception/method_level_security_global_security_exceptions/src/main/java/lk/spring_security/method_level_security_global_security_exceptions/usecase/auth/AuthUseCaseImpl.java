package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.IdentityManager;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginResult;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterResult;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase {

    //inject required classes and spring classes via constructor injection
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final IdentityManager identityManager;

    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            IdentityManager identityManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.identityManager = identityManager;
    }


    //register user
    @Override
    public RegisterResult register(RegisterCommand registerCommand) {
        //check incoming fields
        if(registerCommand.email().isEmpty() || registerCommand.password().isEmpty()){
            throw new IllegalArgumentException("email or password is empty");
        }

        //check email availability
        if(userRepository.findByEmail(registerCommand.email()).isPresent()){
            throw new IllegalArgumentException("email already exists");
        }
        //create user model
        User createNewUser = User.createUser(
                registerCommand.email(),
                passwordEncoder.encode(registerCommand.password()),
                null
        );
        //set role through the domain
        createNewUser.roleUser();
        //save user
        User savedUser = userRepository.saveUser(createNewUser);
        //generate token
        String token = jwtService.generateToken(createNewUser);

        return new RegisterResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                token,
                savedUser.getRole().toString()
        );
    }

    //login user
    @Override
    public LoginResult login(LoginCommand loginCommand) {

        if(loginCommand.email().isEmpty() || loginCommand.password().isEmpty()){
            throw new IllegalArgumentException("email or password is empty");
        }
        //check username and password using spring security
        identityManager.authenticate(loginCommand.email(), loginCommand.password());

        //check and find user in db
        User user = userRepository.findByEmail(loginCommand.email())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        //generate token
        String token = jwtService.generateToken(user);

        return new  LoginResult(
                user.getUserId(),
                user.getEmail(),
                token,
                user.getRole().toString()
        );
    }

}

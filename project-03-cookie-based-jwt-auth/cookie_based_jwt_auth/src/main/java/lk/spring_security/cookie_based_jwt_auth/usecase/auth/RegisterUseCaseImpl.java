package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Role;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterResult;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUseCaseImpl implements RegisterUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;

    public RegisterUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
    }

    //register user
    @Override
    public RegisterResult register(RegisterCommand registerCommand) {

        //check incoming fields
        if(registerCommand.email() == null || registerCommand.password() == null) {
            throw  new IllegalArgumentException("email or password cannot be empty");
        }
        //check user existence
        if(userRepository.userFindByEmail(registerCommand.email()).isPresent()){
            throw new IllegalStateException("email already exists");
        }
        //create new user
        User newUser  = User.createNewUserModel(
                registerCommand.email(),
                passwordEncoder.encode(registerCommand.password()),
                Role.USER
        );
        //save user
        User savedUser = userRepository.registerUser(newUser);
        //generate token
        String token = cookieService.generateToken(newUser);

        return new RegisterResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().toString(),
                token
        );
    }
}

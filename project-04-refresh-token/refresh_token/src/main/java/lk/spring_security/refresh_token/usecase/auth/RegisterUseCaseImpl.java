package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUseCaseImpl implements RegisterUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //register user
    @Override
    public RegisterResult register(RegisterCommand registerCommand) {
        //check incoming fields are empty
        if (registerCommand.email() == null || registerCommand.password() == null) {
            throw new IllegalArgumentException("Fields email and password are mandatory");
        }
        //check user email exists
        if (userRepository.findByEmail(registerCommand.email()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        //create new user
        User newUser = User.createUser(
                registerCommand.email(),
                passwordEncoder.encode(registerCommand.password()),
                null
        );
        //set role
        newUser.setDefaultRole();
        //save
        User savedUser = userRepository.registerUser(newUser);

        return new RegisterResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().toString()
        );
    }
}

package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.enums.Role;
import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUseCaseImpl implements RegisterUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUseCaseImpl(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResult register(RegisterCommand registerCommand) {

        if(registerCommand.email() == null || registerCommand.password() == null) {
            throw new IllegalArgumentException("Fields email and password are mandatory");
        }

        if(userRepository.findByEmail(registerCommand.email()).isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }

        User newUser = User.createUser(
                e
        )


        //hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set default role as USER
        user.setRole(Role.USER);

        return userRepository.registerUser(user);
    }
}

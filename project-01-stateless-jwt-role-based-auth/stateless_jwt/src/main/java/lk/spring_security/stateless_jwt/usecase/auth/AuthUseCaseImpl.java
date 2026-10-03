package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.stateless_jwt.infrastructure.security.JwtImpl;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginResult;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;
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
    private final IdentityManger identityManger;

    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtImpl jwtImpl, AuthenticationManager authenticationManager, IdentityManger identityManger) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtImpl = jwtImpl;
        this.authenticationManager = authenticationManager;
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
        String token = jwtImpl.generateToken(new CustomUserDetails(newUser));

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

        String token = jwtImpl.generateToken(new CustomUserDetails(user));

        return new LoginResult(
                user.getEmail(),
                token
        );
    }

}
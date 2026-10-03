package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.stateless_jwt.infrastructure.security.JwtImpl;
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

    public AuthUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtImpl jwtImpl, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtImpl = jwtImpl;
        this.authenticationManager = authenticationManager;
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

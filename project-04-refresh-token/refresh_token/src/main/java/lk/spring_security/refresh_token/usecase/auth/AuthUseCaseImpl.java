package lk.spring_security.refresh_token.usecase.auth;

import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.domain.models.Role;
import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.refresh_token.web._shared.services.CookieService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    //inject token expiration time
    private final long refreshTokenExpirationMs;

    public AuthUseCaseImpl(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            long refreshTokenExpirationMs
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }

    //register new user
    @Override
    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }
        //hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set default role as USER
        user.setRole(Role.USER);

        return userRepository.registerUser(user);
    }


    //login user
    @Override
    public void loginUser(
            String email,
            String password,
            HttpServletResponse httpServletResponse){

        //check given username and password are correct via auth provider
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        //get user from db
        User exstingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //create access token (15m)
        String accessToken = tokenService.generateToken(exstingUser);
    }
}

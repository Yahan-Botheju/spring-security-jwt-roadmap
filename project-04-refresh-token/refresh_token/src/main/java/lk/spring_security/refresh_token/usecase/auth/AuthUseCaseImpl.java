package lk.spring_security.refresh_token.usecase.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.domain.models.RefreshToken;
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

import java.time.Instant;
import java.util.UUID;

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

        //create refresh token as UUID string
        String refreshTokenStr = UUID.randomUUID().toString();

        //set refresh token to user
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenStr);
        refreshToken.setUser(exstingUser);

        //include another 7d for expiration
        refreshToken.setExpiryData(Instant.now().plusSeconds(refreshTokenExpirationMs));

        //save refresh token in db
        refreshTokenRepository.saveToken(refreshToken);

        //attach tokens to browser
        cookieService.setAccessTokenCookie(httpServletResponse, accessToken);
        cookieService.setRefreshTokenCookie(httpServletResponse, refreshTokenStr);
    }

    //create new access token by using refresh token
    @Override
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){

        //read refresh token from request
        String requestRefreshToken = cookieService.extractCookieByName(
                httpServletRequest, "refresh_token"
        );

        //check refresh token availability
        if(requestRefreshToken == null){
            throw new UsernameNotFoundException("Refresh token not found");
        }

        //get refresh token from db if not throw an error
        RefreshToken existingRefreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        //check token is expired
        if(existingRefreshToken.getExpiryData().isBefore(Instant.now())){
            //remove from db
            refreshTokenRepository.deleteByUserEmail(existingRefreshToken.getUser().getEmail());
            throw new RuntimeException("Refresh token expired, please try again");
        }

        //create fresh user object and attach new generated access token
        User user = existingRefreshToken.getUser();
        String newAccessToken = tokenService.generateToken(user);

        //attach access token to browser
        cookieService.setAccessTokenCookie(httpServletResponse, newAccessToken);
    }


    //logout user
    @Override
    public void logoutUser(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        //get access token from cookie
        String getAccessToken = cookieService.extractCookieByName(httpServletRequest, "refresh_token");


    }
}

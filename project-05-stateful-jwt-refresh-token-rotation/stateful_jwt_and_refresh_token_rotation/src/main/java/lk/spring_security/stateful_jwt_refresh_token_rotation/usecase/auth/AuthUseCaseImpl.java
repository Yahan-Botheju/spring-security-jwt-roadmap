package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Role;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final WalletRepository walletRepository;

    public AuthUseCaseImpl(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            WalletRepository walletRepository
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.walletRepository = walletRepository;
    }

    //register user
    @Override
    @Transactional
    public void registerUser(User user){
        //check user existence by email
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("User already exists");
        }
        //encode user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set role
        user.setRole(Role.USER);

        //save user
        User savedUser = userRepository.registerUser(user);

        //create wallet and save to user
        Wallet wallet = Wallet.builder()
                .walletBalance(1000.0)
                .user(savedUser)
                .build();

        walletRepository.saveWallet(wallet);
    }

    //login user
    @Override
    @Transactional
    public AuthResult loginUser(
            String email,
            String password,
            HttpServletResponse httpServletResponse
    ){
        //check email password correctness through auth provider
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        //get user from db
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        //generate tokens
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        RefreshToken statfullRefreshToken = RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                .isUsed(false)
                .isRevoked(false)
                .user(user)
                .build();
        refreshTokenRepository.saveRefreshToken(statfullRefreshToken);

        //set as http only cookie to browser
        cookieService.addRefreshTokenCookie(httpServletResponse, refreshToken);

        return  new AuthResult(
                accessToken,
                user.getEmail(),
                user.getRole().name()
        );

    }

    //logout user
    @Override
    public void logout(HttpServletResponse httpServletResponse) {
        cookieService.clearCookie(httpServletResponse);
    }

    //refresh token
    @Override
    public AuthResult refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
       //get token from cookie
        String extractToken = cookieService.extractRefreshTokenFromCookie(httpServletRequest);

        //check token availability
        if(extractToken == null){
            throw new IllegalStateException("Refresh token is missing");
        }
        //get token from db
        RefreshToken storedToken = refreshTokenRepository.findByToken(extractToken)
                .orElseThrow(() -> new IllegalStateException("Invalid refresh token"));

        //get user related to token
        User user = storedToken.getUser();

        //if used token is represented user all tokens are remove from db
        if(storedToken.isUsed()){
            refreshTokenRepository.revokeAllUserTokens(user.getUserId());
            cookieService.clearCookie(httpServletResponse);
            throw new IllegalStateException("Refresh token has been revoked!! Please login again");
        }

        /* __TOKEN_ROTATION_PATH__ */
        //marking old token is used
        storedToken.setUsed(true);
        refreshTokenRepository.saveRefreshToken(storedToken);

    }
}

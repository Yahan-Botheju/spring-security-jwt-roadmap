package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Role;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required dependencies
    private final RefreshTokenReposiroty refreshTokenReposiroty;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final WalletRepository walletRepository;

    public AuthUseCaseImpl(
            RefreshTokenReposiroty refreshTokenReposiroty,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            WalletRepository walletRepository
    ) {
        this.refreshTokenReposiroty = refreshTokenReposiroty;
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
    public User loginUser(
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
    }
}

package lk.spring_security.refresh_token.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.*;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCase;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.refresh_token.usecase.auth.RegisterUseCase;
import lk.spring_security.refresh_token.usecase.auth.RegisterUseCaseImpl;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.usecase.product.ProductUseCaseImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {

    //auth usecase impl
    @Bean
    public AuthUseCase authUseCase(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            @Value("${application.security.jwt.refresh-token-expiration-ms}") long refreshTokenExpirationMs
    ) {
        return new AuthUseCaseImpl(
                refreshTokenRepository ,
                userRepository,
                passwordEncoder,
                cookieService,
                tokenService ,
                authenticationManager,
                refreshTokenExpirationMs);
    }

    //product usecase impl
    @Bean
    public ProductUseCase productUseCase(
            ProductRepository productRepository
    ) {
        return new ProductUseCaseImpl(productRepository);
    }

    /* __ */

    //register user
    @Bean
    public RegisterUseCase registerUseCase(
            UserRepository userRepository
    ){
        return new RegisterUseCaseImpl(userRepository);
    }

}

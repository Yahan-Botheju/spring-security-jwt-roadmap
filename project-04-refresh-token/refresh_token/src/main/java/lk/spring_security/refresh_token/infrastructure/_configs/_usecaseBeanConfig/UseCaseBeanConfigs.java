package lk.spring_security.refresh_token.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.*;
import lk.spring_security.refresh_token.usecase.auth.*;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.usecase.product.ProductUseCaseImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {


    /* __AUTH_USE_CASES__ */

    //register user
    @Bean
    public RegisterUseCase registerUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        return new RegisterUseCaseImpl(userRepository, passwordEncoder);
    }

    //login usecase impl
    @Bean
    public LoginUseCase loginUseCase(
            UserRepository userRepository,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            CookieService cookieService,
            IdentityProvider identityProvider,
            @Value("${application.security.jwt.refresh-token-expiration-ms}") long refreshTokenExpirationMs
    ){
        return new LoginUseCaseImpl(userRepository, tokenService, refreshTokenRepository, cookieService, identityProvider, refreshTokenExpirationMs);
    }

    //logout usecase impl
    @Bean
    public LogoutUseCase logoutUseCase(
            RefreshTokenRepository refreshTokenRepository
    ){
        return new LogoutUseCaseImpl(refreshTokenRepository);
    }

    //refresh token usecase impl
    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
           RefreshTokenRepository refreshTokenRepository,
            TokenService tokenService
    ){
        return new RefreshTokenUseCaseImpl(refreshTokenRepository, tokenService);
    }

    /* __PRODUCTS__*/


    //product usecase impl
    @Bean
    public ProductUseCase productUseCase(
            ProductRepository productRepository
    ) {
        return new ProductUseCaseImpl(productRepository);
    }

}

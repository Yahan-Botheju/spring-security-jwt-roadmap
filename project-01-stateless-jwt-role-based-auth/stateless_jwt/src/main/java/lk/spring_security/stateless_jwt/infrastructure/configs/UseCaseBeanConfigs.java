package lk.spring_security.stateless_jwt.infrastructure.configs;

import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import lk.spring_security.stateless_jwt.infrastructure.security.IdentityManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UseCaseBeanConfigs {

    //identity manager
    @Bean
    public IdentityManger identityManger(
            AuthenticationManager authenticationManager
    ) {
        return new IdentityManagerImpl(authenticationManager);
    }
}

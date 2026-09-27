package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.IdentityManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class PersistenceBeanConfigs {

    /* __IDENTITY_MANAGER_IMPL__ */

    @Bean
    public IdentityManager identityManager(
            AuthenticationManager authenticationManager
    ) {
        return new IdentityManagerImpl(authenticationManager);
    }
}

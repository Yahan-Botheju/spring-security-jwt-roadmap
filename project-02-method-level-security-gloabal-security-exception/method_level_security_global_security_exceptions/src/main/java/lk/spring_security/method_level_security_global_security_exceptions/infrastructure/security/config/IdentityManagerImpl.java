package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.IdentityManager;
import org.springframework.security.authentication.AuthenticationManager;

public class IdentityManagerImpl implements IdentityManager {

    //inject required dependencies
    private final AuthenticationManager authenticationManager;

    public IdentityManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}

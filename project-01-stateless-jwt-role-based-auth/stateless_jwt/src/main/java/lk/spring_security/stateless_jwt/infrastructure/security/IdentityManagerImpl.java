package lk.spring_security.stateless_jwt.infrastructure.security;

import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import org.springframework.security.authentication.AuthenticationManager;

public class IdentityManagerImpl implements IdentityManger {

    private final AuthenticationManager authenticationManager;

    public IdentityManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void authenticateUser(String email, String password) {

    }
}

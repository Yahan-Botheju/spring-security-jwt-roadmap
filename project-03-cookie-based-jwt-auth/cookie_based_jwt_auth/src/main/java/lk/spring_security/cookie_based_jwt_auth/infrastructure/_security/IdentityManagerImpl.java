package lk.spring_security.cookie_based_jwt_auth.infrastructure._security;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import org.springframework.security.authentication.AuthenticationManager;

public class IdentityManagerImpl implements IdentityManager {

    private final AuthenticationManager authenticationManager;

    public IdentityManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void authenticate(String email, String password) {
        aut
    }
}

package lk.spring_security.cookie_based_jwt_auth.infrastructure._security;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class IdentityManagerImpl implements IdentityManager {

    private final AuthenticationManager authenticationManager;

    public IdentityManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //authenticate username and password
    @Override
    public void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}

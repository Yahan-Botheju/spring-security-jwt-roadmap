package lk.spring_security.stateless_jwt.infrastructure.security;

import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class IdentityManagerImpl implements IdentityManger {

    //inject required dependencies
    private final AuthenticationManager authenticationManager;

    public IdentityManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //authenticate username and password
    @Override
    public void authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}

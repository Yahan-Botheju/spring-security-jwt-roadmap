package lk.spring_security.refresh_token.infrastructure._security;

import lk.spring_security.refresh_token.domain.repositories.IdentityProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class IdentityProviderImpl implements IdentityProvider {

    //inject required dependencies
    private final AuthenticationManager authenticationManager;

    public IdentityProviderImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //auth username and password
    @Override
    public void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}

package lk.spring_security.refresh_token.infrastructure._security;

import lk.spring_security.refresh_token.domain.repositories.IdentityProvider;
import org.springframework.security.authentication.AuthenticationManager;

public class IdentityProviderImpl implements IdentityProvider {

    //inject required dependencies
    private final AuthenticationManager authenticationManager;

    public IdentityProviderImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}

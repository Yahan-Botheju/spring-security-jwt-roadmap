package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.identity_provider;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class IdentityProviderImpl implements IdentityProvider {

    //inject required dependencies
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public IdentityProviderImpl(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    //password encoder
    @Override
    public String passwordEncoder(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    //authenticate user through auth manager
    @Override
    public void authenticate(String username, String password){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}

package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.identity_provider;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records.AuthenticatedUser;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public AuthenticatedUser authenticate(String username, String password){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return new AuthenticatedUser(
                null,
                customUserDetails.getUsername(),
                customUserDetails.getUser().getRole().name()
        );
    }
}

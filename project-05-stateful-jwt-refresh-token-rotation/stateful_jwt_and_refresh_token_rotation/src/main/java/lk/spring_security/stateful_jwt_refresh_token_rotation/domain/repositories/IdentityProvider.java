package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records.AuthenticatedUser;

public interface IdentityProvider {

    //password encoder
    String passwordEncoder(String rawPassword);

    //authenticate user through auth manager
    AuthenticatedUser authenticate(String username, String password);
}

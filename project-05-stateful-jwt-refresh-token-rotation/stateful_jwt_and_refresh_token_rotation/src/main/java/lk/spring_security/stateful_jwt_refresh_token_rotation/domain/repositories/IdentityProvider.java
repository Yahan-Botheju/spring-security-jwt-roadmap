package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthResult;

public interface IdentityProvider {

    //password encoder
    String passwordEncoder(String rawPassword);

    //authenticate user through auth manager
    AuthResult authenticate(String username, String password);
}

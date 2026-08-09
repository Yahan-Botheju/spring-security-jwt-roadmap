package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

public interface IdentityProvider {

    //password encoder
    String passwordEncoder(String rawPassword);


}

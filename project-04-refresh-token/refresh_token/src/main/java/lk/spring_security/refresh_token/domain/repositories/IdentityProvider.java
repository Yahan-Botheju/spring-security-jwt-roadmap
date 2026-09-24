package lk.spring_security.refresh_token.domain.repositories;

public interface IdentityProvider {
    void authenticate(String email, String password);
}

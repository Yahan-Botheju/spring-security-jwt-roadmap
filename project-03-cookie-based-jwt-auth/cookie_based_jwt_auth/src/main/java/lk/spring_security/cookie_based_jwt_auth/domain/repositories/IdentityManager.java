package lk.spring_security.cookie_based_jwt_auth.domain.repositories;

public interface IdentityManager {
    //authenticate username and paw
    void authenticate(String email, String password);
}
